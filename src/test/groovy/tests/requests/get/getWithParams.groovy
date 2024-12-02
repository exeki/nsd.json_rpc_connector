package tests.requests.get

import ru.kazantsev.nsd.json_rpc_connector.RpcRequestDto
import ru.kazantsev.nsd.json_rpc_connector.RpcResponseDto

import static tests.TestUtils.*

RpcRequestDto.Get dto = new RpcRequestDto.Get("employee", ["UUID" : ut.eq('employee$12192601')])
dto.setView(["UUID", 'title', 'login'])
RpcResponseDto result = connector.sendRequest(dto)
logger.info(objectMapper.writeValueAsString(result))
