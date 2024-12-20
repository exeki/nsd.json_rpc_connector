package tests.requests.create

import ru.kazantsev.nsd.json_rpc_connector.RpcRequestDto
import ru.kazantsev.nsd.json_rpc_connector.RpcResponseDto

import static tests.TestUtils.*;

HashMap attrs = new HashMap()
attrs.put("clientName", "Казанцев Егор")
attrs.put("clientEmail", "myemail@somedomain.com")
attrs.put("clientPhone", "8(800)555-35-35")
attrs.put("service", 'slmService$23495801')
attrs.put("agreement", 'agreement$12435404')
attrs.put("shortDescr", "Какая то тема")
attrs.put("descriptionRTF", "бр<br>бр")
RpcRequestDto.Create dto = new RpcRequestDto.Create('serviceCall$vnINC', attrs)
dto.setView(["UUID", "title"])
RpcResponseDto result = connector.sendRequest(dto)
logger.info(objectMapper.writeValueAsString(result))
