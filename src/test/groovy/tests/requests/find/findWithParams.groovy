package tests.requests.find

import ru.kazantsev.nsd.json_rpc_connector.RpcRequestDto
import ru.kazantsev.nsd.json_rpc_connector.RpcResponseDto

import static tests.TestUtils.*

RpcRequestDto.Find dto = new RpcRequestDto.Find("employee", ["title" : ut.like('%Буров%')])
dto.setView(["UUID", "title"])
RpcResponseDto result = connector.sendRequest(dto)
logger.info(objectMapper.writeValueAsString(result))
