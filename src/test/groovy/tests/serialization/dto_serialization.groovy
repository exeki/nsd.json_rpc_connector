package tests.serialization

import ru.kazantsev.nsd.json_rpc_connector.RpcRequestDto

import static tests.TestUtils.*

RpcRequestDto.Edit dto = new RpcRequestDto.Edit("employee", ["UUID" : ut.eq('employee$12192601')], ["title" : "12312312"])
logger.info(dto.getJsonValue()?.toString())
