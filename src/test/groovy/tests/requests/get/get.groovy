package tests.requests.get

import ru.kazantsev.nsd.json_rpc_connector.Query
import ru.kazantsev.nsd.json_rpc_connector.RpcRequestDto
import ru.kazantsev.nsd.json_rpc_connector.RpcResponseDto

import static tests.TestUtils.*

RpcRequestDto.Get dto = new RpcRequestDto.Get("employee", new Query().put("UUID", ut.eq('employee$12192601')))

RpcResponseDto result = connector.sendRequest(dto)
logger.info(objectMapper.writeValueAsString(result))