#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
import org.apache.isis.applib.fixturescripts.FixtureScript;
import lombok.Getter;
import lombok.Setter;

#set( 
$entityName = 
$EntityName.substring(0,1).toLowerCase().concat($EntityName.substring(1)) 
)

public class ${EntityName}Create extends FixtureScript {

    @Getter @Setter
    private ${PropertyType} ${property};

    @Getter
    private ${EntityName} ${entityName};

    @Override
    protected void execute(final FixtureScript.ExecutionContext ec) {

        ${PropertyType} ${property} = checkParam("${property}", ec, ${PropertyType}.class);

        this.${entityName} = wrap(menu).create(name);

        // also make available to UI
        ec.addResult(this, ${entityName});
    }

    @javax.inject.Inject
    ${EntityName}Menu menu;
}
