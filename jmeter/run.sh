#! /bin/bash
# run a jmeter testplan inside the jmeter container


jmeter_cid=$(docker ps -q -f "label=role=jmeter")

docker exec $jmeter_cid jmeter -n -f -JHOST=app -JUSERS=10 -JLOOPS=1000 -t /tests/TestPlan.jmx -l /tests/results.jtl
