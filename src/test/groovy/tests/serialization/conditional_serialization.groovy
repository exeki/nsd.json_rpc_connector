package tests.serialization


import ru.kazantsev.nsd.json_rpc_connector.Condition

import static tests.TestUtils.*

logger.info(objectMapper.writeValueAsString(new Condition.Like("&somevalue&")))
