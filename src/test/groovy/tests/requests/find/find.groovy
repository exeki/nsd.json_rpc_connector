package tests.requests.find

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