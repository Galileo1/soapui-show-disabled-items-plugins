package trademe;

import java.util.ArrayList;
import java.util.List;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.ModelItem;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.plugins.ActionConfiguration;
import com.eviware.soapui.support.UISupport;
import com.eviware.soapui.support.action.support.AbstractSoapUIAction;
import com.eviware.soapui.support.components.ModelItemListDesktopPanel;


@ActionConfiguration(actionGroup = "EnabledWsdlProjectActions")
public class MyAction extends AbstractSoapUIAction<WsdlProject> {

    public MyAction() {
        super("Show Disabled Items", "A plugin action at the project level");
    }

    @Override
    public void perform(WsdlProject wsdlProject, Object o) {
        //UISupport.showInfoMessage("Hello from [" + wsdlProject.getName() + "]!");
        List<ModelItem> disabledResult = new ArrayList<>();
        //get all test suites in the project 
        List<TestSuite> allTestSuites = new ArrayList<TestSuite>();
        allTestSuites = wsdlProject.getTestSuiteList();
        //get all disabled test suites in the project 
        List<TestSuite> disabledTestSuites = new ArrayList<TestSuite>();
        disabledTestSuites  = getDisabledTestSuites(allTestSuites);
        //get all disabled test cases from the project 
        List<TestCase> disabledTestCases = new ArrayList<TestCase>();
        disabledTestCases = getDisabledTestCases(allTestSuites);
        if (disabledTestSuites.isEmpty() && disabledTestCases.isEmpty()){
        	UISupport.showInfoMessage("Somebody did an awesome job!! All testcases and testsuites are enabled in the project");
        } else {
        	if (disabledTestSuites.size() > 0){
        		disabledResult.addAll(disabledTestSuites);
        	}
        	
        	if (disabledTestCases.size() > 0){
        		disabledResult.addAll(disabledTestCases);
        	}        	
        	
        	UISupport.showDesktopPanel(new ModelItemListDesktopPanel("Search Results",
                     "The following items are disabled",                
                     disabledResult.toArray(new ModelItem[disabledResult.size()])));        	
        }               
        
    }
    

	private List<TestCase> getDisabledTestCases(List<TestSuite> allTestSuites) {
		List<TestCase> localDisabledTCList = new ArrayList<TestCase>();
		for (TestSuite ts : allTestSuites){
			List<TestCase> localTCList = new ArrayList<TestCase>();
			localTCList = ts.getTestCaseList();
			for (TestCase tc : localTCList ){
				if (tc.isDisabled()){
					localDisabledTCList.add(tc);
				}	
			}
		}
		
		return localDisabledTCList;
		
		
	}

	private List<TestSuite> getDisabledTestSuites(List<TestSuite> allTestSuites) {
		List<TestSuite> localDisabledTSList = new ArrayList<TestSuite>();	
		for (TestSuite ts : allTestSuites){
			if (ts.isDisabled()){
				localDisabledTSList.add(ts);
			}
		}
		
		return localDisabledTSList;
		
	}
}
