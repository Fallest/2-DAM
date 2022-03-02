-- Drop triggers
begin 
	for i in (select trigger_name, owner from dba_triggers where owner = 'SCOTT') loop
		execute immediate 'drop trigger ' || i.owner || '.' || i.trigger_name;
	end loop;
end;

-- Drop tables
begin 
	for i in (select object_name from user_objects where object_type = 'TABLE') loop
		execute immediate 'drop table ' || i.object_name || ' cascade constraints';
	end loop;
end;

-- Drop views
begin
	for i in (select object_name from user_objects where object_type = 'VIEW') loop
		execute immediate 'drop view ' ||i.object_name;
	end loop;
end;

-- Drop procedures
begin
	for i in (select object_name from user_objects where object_type = 'PROCEDURE') loop
		execute immediate 'drop procedure ' ||i.object_name;
	end loop;
end;

-- Drop functions
begin
	for i in (select object_name from user_objects where object_type = 'FUNCTION') loop
		execute immediate 'drop function ' ||i.object_name;
	end loop;
end;

-- Drop types
begin
	for i in (select object_name from user_objects where object_type = 'TYPE') loop
		execute immediate 'drop type ' ||i.object_name;
	end loop;
end;

-- Drop package bodies
begin
	for i in (select object_name from user_objects where object_type = 'PACKAGE BODY') loop
		execute immediate 'drop package body ' ||i.object_name;
	end loop;
end;

-- Drop packages
begin
	for i in (select object_name from user_objects where object_type = 'PACKAGE') loop
		execute immediate 'drop package ' ||i.object_name;
	end loop;
end;