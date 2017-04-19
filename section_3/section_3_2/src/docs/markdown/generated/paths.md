## Paths
### welcome
```
GET /simple/welcome
```

#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|string|
|500|500 message|Error|


#### Consumes

* application/json

#### Produces

* */*

#### Tags

* simple-controller

### postSimpleValue
```
POST /simple/{param}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|param|param|true|string||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|OK|string|


#### Consumes

* text/plain

#### Produces

* */*

#### Tags

* simple-controller

