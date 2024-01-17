package tests.serialization

import ru.kazantsev.nsd.json_rpc_connector.Attrs
import ru.kazantsev.nsd.json_rpc_connector.Query
import ru.kazantsev.nsd.json_rpc_connector.RpcRequestDto

import static tests.TestUtils.*

RpcRequestDto.Edit dto = new RpcRequestDto.Edit("employee", new Query().put("UUID", ut.eq('employee$12192601')), new Attrs().put("title", "12312312"))
logger.info(dto.getJsonValue()?.toString())
