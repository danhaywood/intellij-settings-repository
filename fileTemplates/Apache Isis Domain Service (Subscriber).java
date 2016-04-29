#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import org.apache.isis.applib.annotation.*;

@org.apache.isis.applib.annotation.DomainService(
    nature = org.apache.isis.applib.annotation.NatureOfService.DOMAIN
)
@org.apache.isis.applib.annotation.DomainServiceLayout(
    menuOrder = "1"
)
public class ${SubscriberName}Subscriber extends org.apache.isis.applib.AbstractSubscriber {


}
