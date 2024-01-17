# Общее описание:

Данная библиотека используется для обращения к модулю [https://github.com/exeki/ru.itsm365.jsonRpc](jsonRpc) NSD.
Предоставляет полный функционал модуля, который можно использовать разными способами. 
Библиотека основа на библиотеке [https://github.com/exeki/nsd.basic_api_connector](nsd.basic_api_connector) и использует ее как зависимость.

# Как это работает:

Как и в случае с nsd.basic_api_connector, входным классом является Connector (ru.kazantsev.nsd.json_rpc_connector.Connector).
Можно использовать заранее подготовленные методы get, find, create, edit, либо подготовить один или несколько DTO RpcRequestDto и отправить
их методом sendRequest.

Так же в модуле есть утилитарый класс RpcUtilities, который содержит методы для создания DTO RpcRequestDto и условных операций.

# Примеры:

### Создания

В данном примере отправка запроса проиходит с предварительным созданием DTO. DTO создается при помощи конструктора. 
Это же действие можно было выполнить при помощи метода Connector.jsonRpcCreate().

```groovy
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ru.kazantsev.nsd.basic_api_connector.ConnectorParams
import ru.kazantsev.nsd.json_rpc_connector.Attrs
import ru.kazantsev.nsd.json_rpc_connector.Connector
import ru.kazantsev.nsd.json_rpc_connector.RpcRequestDto
import ru.kazantsev.nsd.json_rpc_connector.RpcResponseDto

Logger logger = LoggerFactory.getLogger("CREATE TEST")

//Создается ассотиативный массив для редактирования,
//содержий в ключах коды атрибутов,
//в значениях значения атрибутов создаваемого объекта
Attrs attrs = new Attrs()
        .put("clientName", "Казанцев Егор")
        .put("clientEmail", "myemail@somedomain.com")
        .put("clientPhone", "8(800)555-35-35")
        .put("service", 'slmService$23495801')
        .put("agreement", 'agreement$12435404')
        .put("shortDescr", "Какая то тема")
        .put("descriptionRTF", "бр<br>бр")

//Создается новый коннектор
Connector connector = new Connector(ConnectorParams.byConfigFile("DSO_TEST"))
//Создается DTO метода create для последующей отправки 
RpcRequestDto.Create dto = new RpcRequestDto.Create('serviceCall$vnINC', attrs)
//Запрос отправляется с ранее созданным DTO
RpcResponseDto result = connector.sendRequest(dto)

logger.info(new ObjectMapper().writeValueAsString(result))
```

### Редактирование

В данном примере отправка запроса проиходит без предварительного созания запроса.
Это же действие можно было выполнить при помощи предваритального создания DTO.

```groovy
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ru.kazantsev.nsd.basic_api_connector.ConnectorParams
import ru.kazantsev.nsd.json_rpc_connector.Attrs
import ru.kazantsev.nsd.json_rpc_connector.Connector
import ru.kazantsev.nsd.json_rpc_connector.Query
import ru.kazantsev.nsd.json_rpc_connector.RpcResponseDto
import ru.kazantsev.nsd.json_rpc_connector.RpcUtilities

Logger logger = LoggerFactory.getLogger("CREATE TEST")

//Создается экземпляр утилитарного класса
RpcUtilities rpcUt = RpcUtilities.getInstance()

//Создается ассотиативный массив для редактирования,
//содержий в ключах коды атрибутов,
//в значениях значения атрибутов создаваемого объекта
Attrs attrs = rpcUt.attrs()
        .put("shortDescr", "123131sdfsdfsdfsdfsf23123")
        .put("descriptionRTF", "3213123asfasfwsdefs123123")

//Создается поисковый массив при помощи утилитарного класса,
//в него добавляется условие соответствия, которое так же создается при помощи утилитарного класса
Query query = rpcUt.query().put("UUID", rpcUt.eq('serviceCall$59374017'))

//Создается новый коннектор
Connector connector = new Connector(ConnectorParams.byConfigFile("DSO_TEST"))

//Запрос отправляется без предвариательно создания DTO
RpcResponseDto result = connector.jsonRpcEdit(
        "serviceCall",
        query,
        attrs,
        null,
        null)

logger.info(new ObjectMapper().writeValueAsString(result))
```

### Несколько методов в одном запросе

Создается несколько DTO с разными методами и отправляется в одном запросе.

```groovy
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ru.kazantsev.nsd.basic_api_connector.ConnectorParams
import ru.kazantsev.nsd.json_rpc_connector.Attrs
import ru.kazantsev.nsd.json_rpc_connector.Connector
import ru.kazantsev.nsd.json_rpc_connector.Query
import ru.kazantsev.nsd.json_rpc_connector.RpcRequestDto
import ru.kazantsev.nsd.json_rpc_connector.RpcResponseDto
import ru.kazantsev.nsd.json_rpc_connector.RpcUtilities

Logger logger = LoggerFactory.getLogger("CREATE TEST")
RpcUtilities rpcUt = RpcUtilities.getInstance()

//Создается новый коннектор
Connector connector = new Connector(ConnectorParams.byConfigFile("DSO_TEST"))

//DTO для метода get
RpcRequestDto.Get get = rpcUt.get("employee", new Query().put("UUID", rpcUt.eq('employee$12192601')))
get.setId(1)

//DTO для метода find
RpcRequestDto.Find find = rpcUt.find("employee", new Query().put("title", rpcUt.like("%Буров%")))
find.setId(2)
find.setView(['UUID', 'title'])

Attrs attrs = new Attrs()
        .put("clientName", "Казанцев Егор")
        .put("clientEmail", "myemail@somedomain.com")
        .put("clientPhone", "8(800)555-35-35")
        .put("service", 'slmService$23495801')
        .put("agreement", 'agreement$12435404')
        .put("shortDescr", "Какая то тема")
        .put("descriptionRTF", "бр<br>бр")

//DTO для метода create
RpcRequestDto.Create create = new RpcRequestDto.Create('serviceCall$vnINC', attrs)
create.setId(3)

//Все три DTO отправляются одним запросом, все результаты будут возвращены в массив result
//объекты в result будут разбираться в соответствии с id
List<RpcResponseDto> result = connector.sendRequest([get, find, create])
logger.info(new ObjectMapper().writeValueAsString(result))
```

### Поиск объектов

Поиск объектов проиходит с предварительным созданием DTO.
Утилитарный класс не используется, все объекты создаются при помощи конструкторов. 
В запросе задается limit и offset.

```groovy
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ru.kazantsev.nsd.basic_api_connector.ConnectorParams
import ru.kazantsev.nsd.json_rpc_connector.Condition
import ru.kazantsev.nsd.json_rpc_connector.Connector
import ru.kazantsev.nsd.json_rpc_connector.Query
import ru.kazantsev.nsd.json_rpc_connector.RpcRequestDto
import ru.kazantsev.nsd.json_rpc_connector.RpcResponseDto

Logger logger = LoggerFactory.getLogger("CREATE TEST")
Connector connector = new Connector(ConnectorParams.byConfigFile("DSO_TEST"))

Query query = new Query().put("number", new Condition.Between(400, 1200))
RpcRequestDto.Find dto = new RpcRequestDto.Find("serviceCall", query)
dto.setId(321123)
dto.setLimit(300)
dto.setOffset(500)
dto.setView(['number', 'UUID'])
RpcResponseDto result = connector.sendRequest(dto)
logger.info(new ObjectMapper().writeValueAsString(result))
```