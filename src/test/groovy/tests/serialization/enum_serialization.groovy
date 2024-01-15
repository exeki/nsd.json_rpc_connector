package tests.serialization


import ru.kazantsev.nsd.json_rpc_connector.Method
import tests.TestUtils

import static tests.TestUtils.*

TestUtils.logger.info(TestUtils.objectMapper.writeValueAsString(Method.CREATE).size().toString())

