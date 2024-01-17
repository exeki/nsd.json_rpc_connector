package tests.requests.multi

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