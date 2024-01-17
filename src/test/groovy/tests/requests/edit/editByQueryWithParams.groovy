package tests.requests.edit

import ru.kazantsev.nsd.json_rpc_connector.Attrs
import ru.kazantsev.nsd.json_rpc_connector.Query
import ru.kazantsev.nsd.json_rpc_connector.RpcRequestDto
import ru.kazantsev.nsd.json_rpc_connector.RpcResponseDto

import static tests.TestUtils.*;

Attrs attrs = new Attrs()
attrs.put("shortDescr", "qweasdzxc")
attrs.put("descriptionRTF", "qweasdzxc")
Query query = new Query().put("UUID", ut.eq('serviceCall$59319750'))
RpcRequestDto.Edit dto = new RpcRequestDto.Edit("serviceCall", query, attrs)
dto.setView(["UUID", "title"])
RpcResponseDto result = connector.sendRequest(dto)
logger.info(objectMapper.writeValueAsString(result))
