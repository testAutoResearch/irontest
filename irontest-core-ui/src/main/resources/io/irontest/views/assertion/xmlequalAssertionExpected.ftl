<#escape x as x?html>${ (verification.assertion.otherProperties??)?then(verification.assertion.otherProperties.expectedXML, 'null') }</#escape>.