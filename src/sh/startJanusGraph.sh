${janusgraph_dir}/elasticsearch/bin/elasticsearch -d
JANUSGRAPH_YAML=${janusgraph_dir}/conf/gremlin-server/jhtools.yaml ${janusgraph_dir}/bin/gremlin-server.sh start