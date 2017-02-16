//https://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html
IF EXISTS(SELECT 1
          FROM   INFORMATION_SCHEMA.ROUTINES
          WHERE  ROUTINE_NAME = 'product_hierarchy'
                )
BEGIN
	DROP PROCEDURE dbo.[product_hierarchy]
END

GO
CREATE   PROCEDURE product_hierarchy AS
  With items(id, name,parent_id, level, path) as(
	SELECT id as id, 
			name, 
			parent_id as parent_id, 
			0 AS level,
			CAST(name as VARCHAR(255)) as path 
	FROM [dbo].[product] as p
	where parent_id is null and p.folder=1
  UNION ALL
	SELECT i.id,
		 i.name, 
		 i.parent_id , 
		  level + 1 ,
		CAST(path + '.'+ CAST(i.name AS VARCHAR(255)) AS VARCHAR(255))
	FROM [dbo].[product] as i
	
	INNER JOIN items itms 
	ON itms.id = i.parent_id
	 where i.folder=1
  )
  SELECT 
  CAST(i.id AS BINARY(16)) as id, 
  CAST(i.parent_id AS BINARY(16)) as parent_id,
  i.name, 
  i.level, 
  i.path
     from items as i  
  order by path asc, level asc