#!/bin/sh
git pull

mvn install -Pprod

docker build -t registry-vpc.cn-shanghai.aliyuncs.com/naquyong/suiniyong-manager:prod .

docker push registry-vpc.cn-shanghai.aliyuncs.com/naquyong/suiniyong-manager:prod

curl 'https://cs.console.aliyun.com/hook/trigger?triggerUrl=Y2U5YzFkM2QwYjRiNzRkOWRhYTM2MzA3OTg4MmUxZDkyfHN1aW5peW9uZy1tYW5hZ2VyfHJlZGVwbG95fDFhdXVya3A0c3BtdDh8&secret=53586271374b45646a6a4378744d464b493012a241d3ce7ce901d7afcc1afc1f'