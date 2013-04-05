package com.nw.rads.estimation

class DataLoader {
	static void loadLineOfBusiness(){
		def ls = "eB2B funded,Actuarial,CIM-ECIF,Finance,I&O,IAS,IIG,iMedia,IP,Marketing Services, Nationwide Bank,NFG,NFN,NI,RP,Sales"
    	ls.split(',').each {
    		LineOfBusiness.findOrSave(name:it)
    	}
	}

	static void loadTeams () {
		def ls = ["CPFR CEL" ,"CPFR Pres Sys" ,"CPFR PT" ,"CPFR PVVB" ,"eB2B" ,"IDS BSA" ,"IDS Data Srv CLS SmallProj" ,"IDS Data Services Project" ,"IDS Sales"]
		ls.each {
			Team.findOrSave(name:it)
		}


	}
}

