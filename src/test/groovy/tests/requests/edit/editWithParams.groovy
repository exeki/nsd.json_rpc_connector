package tests.requests.edit

import ru.kazantsev.nsd.json_rpc_connector.RpcRequestDto
import ru.kazantsev.nsd.json_rpc_connector.RpcResponseDto

import static tests.TestUtils.*;

HashMap attrs = new HashMap()
attrs.put("shortDescr", "12313123123")
attrs.put("descriptionRTF", "3213123123123")
RpcRequestDto.Edit dto = new RpcRequestDto.Edit('serviceCall$59319750', attrs)
dto.setView(["UUID", "title"])
RpcResponseDto result = connector.sendRequest(dto)
logger.info(objectMapper.writeValueAsString(result))
