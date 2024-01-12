package tests


import ru.kazantsev.nsd.json_rpc_connector.Method

import static tests.TestUtils.*

logger.info(objectMapper.writeValueAsString(Method.CREATE).size().toString())

