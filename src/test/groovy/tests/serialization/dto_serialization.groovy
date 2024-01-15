package tests.serialization


import ru.kazantsev.nsd.json_rpc_connector.Method
import ru.kazantsev.nsd.json_rpc_connector.Params
import ru.kazantsev.nsd.json_rpc_connector.Query
import ru.kazantsev.nsd.json_rpc_connector.RequestDto

import static tests.TestUtils.*

Params.Get params = new Params.Get("employee", new Query().put("UUID", ut.eq('employee$12192601')))
RequestDto dto = new RequestDto(Method.GET, params);
logger.info(objectMapper.writeValueAsString(dto))
