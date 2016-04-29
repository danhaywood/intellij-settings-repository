#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.fixturescripts.*;
import javax.jdo.annotations.*;
import lombok.Getter;
import lombok.Setter;

#set( 
$entityName = 
$EntityName.substring(0,1).toLowerCase().concat($EntityName.substring(1)) 
)

#set( 
$Property = 
$property.substring(0,1).toUpperCase().concat($property.substring(1)) 
)

public class ${EntityName}RowHandler implements org.isisaddons.module.excel.dom.ExcelFixtureRowHandler {

    @Getter @Setter
    private ${PropertyType} $property;

    @Override
    public java.util.List<Object> handleRow(
            final org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext executionContext,
            final org.isisaddons.module.excel.dom.ExcelFixture excelFixture,
            final Object previousRow) {

        final ${EntityName} ${entityName} = repository.findOrCreate(${property});

        // TODO: default other properties not in spreadsheet

        executionContext.addResult(excelFixture, ${entityName});
        return Collections.singletonList(${entityName});
    }

    @javax.inject.Inject
    ${EntityName}Repository repository;
}
