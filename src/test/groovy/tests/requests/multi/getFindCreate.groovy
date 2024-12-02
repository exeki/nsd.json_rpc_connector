package tests.requests.multi

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ru.kazantsev.nsd.basic_api_connector.ConnectorParams
import ru.kazantsev.nsd.json_rpc_connector.Connector
import ru.kazantsev.nsd.json_rpc_connector.RpcRequestDto
import ru.kazantsev.nsd.json_rpc_connector.RpcResponseDto
import ru.kazantsev.nsd.json_rpc_connector.ConditionUtilities

Logger logger = LoggerFactory.getLogger("CREATE TEST")
ConditionUtilities rpcUt = ConditionUtilities.getInstance()

//Создается новый коннектор
Connector connector = new Connector(ConnectorParams.byConfigFile("DSO_TEST"))

//DTO для метода get
RpcRequestDto.Get get = new RpcRequestDto.Get("employee", ["UUID": rpcUt.eq('employee$12192601')])
get.setId(1)

//DTO для метода find
RpcRequestDto.Find find = new RpcRequestDto.Find("employee", ["title": rpcUt.like("%Буров%")])
find.setId(2)
find.setView(['UUID', 'title'])

HashMap attrs = new HashMap()
attrs.put("clientName", "Казанцев Егор")
attrs.put("clientEmail", "myemail@somedomain.com")
attrs.put("clientPhone", "8(800)555-35-35")
attrs.put("service", 'slmService$23495801')
attrs.put("agreement", 'agreement$12435404')
attrs.put("shortDescr", "Какая то тема")
attrs.put("descriptionRTF", "бр<br>бр")

//DTO для метода create
RpcRequestDto.Create create = new RpcRequestDto.Create('serviceCall$vnINC', attrs)
create.setId(3)

//Все три DTO отправляются одним запросом, все результаты будут возвращены в массив result
//объекты в result будут разбираться в соответствии с id
List<RpcResponseDto> result = connector.sendRequest([get, find, create])
logger.info(new ObjectMapper().writeValueAsString(result))
