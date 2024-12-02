package tests.requests.find

import groovy.time.TimeCategory
import ru.kazantsev.nsd.json_rpc_connector.Condition

import ru.kazantsev.nsd.json_rpc_connector.RpcRequestDto
import ru.kazantsev.nsd.json_rpc_connector.RpcResponseDto

import static tests.TestUtils.*

Integer EMPLOYEE_HOURS_DELAY = 24

String[] ous = [
        'ou$16760107',
        'ou$50666306',
        'ou$116578201'
]
RpcRequestDto.Find dto = new RpcRequestDto.Find(
        "employee",
        ["parent" : new Condition.In(ous), "lastModifiedDate" : new Condition.Gt(use(TimeCategory) { new Date() - EMPLOYEE_HOURS_DELAY.days })]
)
dto.setView(["UUID", "title"])
RpcResponseDto result = connector.sendRequest(dto)
logger.info(objectMapper.writeValueAsString(result))
