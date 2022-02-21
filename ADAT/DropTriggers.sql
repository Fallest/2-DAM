begin 
	for i in (select trigger_name, owner from dba_triggers where owner = 'SCOTT') loop
		execute immediate 'drop trigger ' || i.owner || '.' || i.trigger_name;
	end loop;
end;