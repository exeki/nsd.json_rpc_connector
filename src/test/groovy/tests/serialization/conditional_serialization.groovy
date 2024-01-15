package tests.serialization

import ru.kazantsev.nsd.json_rpc_connector.ConditionalOperator

import static tests.TestUtils.*

logger.info(objectMapper.writeValueAsString(new ConditionalOperator.Like("&somevalue&")))
