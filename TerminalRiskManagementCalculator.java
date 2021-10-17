package Convertor;


import java.util.Arrays;
import java.util.Scanner;

public class TerminalRiskManagementCalculator {
	
//Byte-1 Data : 	
	public static int EMVCVMReqCapabilities;
	public static int EMVNoCVMReqCapabilities;
	public static int Kernel_Config;
	
	public static int F8=248;
	public static int Kernel_Config_Constant=32;
	public static int counter=0;
	
	
//Byte-2 + Byte-3 Data : 	
	public static int B2_7A_Constant=122;
	public static int B3_C0_Constant=192;
	
	public static int TRM_9F1D_B1;
	public static int TRM_9F1D_B2;
	public static int TRM_9F1D_B3;
	public static int TRM_9F1D_B4=00;
	
	public static int ContactCVMConfig;
	
	public static String hextobinary(String hexadecimal) {
         String Seprate = "--------------------------------";    
        //Converting Hexa decimal number to Decimal in Java
        int decimal = Integer.parseInt(hexadecimal, 16);
     
        System.out.println("Decimal ("+hexadecimal+ ")  = "+ decimal);
   
        //Converting hexadecimal number to binary in Java      
        String binary = Integer.toBinaryString(decimal);
        //System.out.printf("Hexadecimal ("+hexadecimal+ ")  = "+ binary);
        
        System.out.printf("Hexadecimal ("+hexadecimal+ ")  = "+ binary);
        System.out.println();
        //System.out.println("Counter :"+counter);
        if(counter==1){
        	EMVCVMReqCapabilities=decimal;	
        }
        else if(counter==2){
        	EMVNoCVMReqCapabilities=decimal;
        }
        else if(counter==3) {
        	Kernel_Config=decimal;
        	
        }
        
        return Seprate;
	}
	
	
   public static void Byte1() {
	   
	    Scanner scanner = new Scanner(System.in);
	   	
		System.out.println("Enter EMVCVMReqCapabilities : ");
	    String cvm=scanner.next();
	    counter++;
	    System.out.println(hextobinary(cvm));
	    
	    System.out.println("Enter EMVNoCVMReqCapabilities : ");
	    String Nocvm=scanner.next();
	    counter++;
	    System.out.println(hextobinary(Nocvm));
	    
	    System.out.println("Enter Kernel_Config : ");
	    String Kernel_C=scanner.next();
	    counter++;
	    System.out.println(hextobinary(Kernel_C));
	    
	   
	    TRM_9F1D_B1=((EMVCVMReqCapabilities | EMVNoCVMReqCapabilities) & F8) | ((Kernel_Config & Kernel_Config_Constant) >> 3);
	    //System.out.println(((EMVCVMReqCapabilities | EMVNoCVMReqCapabilities) & F8) | ((Kernel_Config & Kernel_Config_Constant) >> 3));
	    System.out.println("9F1D : Byte-1 = "+Integer.toHexString(TRM_9F1D_B1).toUpperCase());
   }	
	
   
   public static void Byte2() {
	    Scanner scanner = new Scanner(System.in);
	    
		System.out.println("==================");
	   	System.out.println();
		System.out.println("Enter ContactCVMConfig : ");
	    String contactcvmconfig=scanner.next();
	    
	    int decimal_Value = Integer.parseInt(contactcvmconfig, 16);
	    System.out.println("Decimal ("+contactcvmconfig+ ")  = "+ decimal_Value);
	    
	    String binary_value= Integer.toBinaryString(decimal_Value);        
        System.out.printf("Hexadecimal ("+binary_value+ ")  = "+ binary_value);

	    ContactCVMConfig=decimal_Value;

	    TRM_9F1D_B2=(ContactCVMConfig & B2_7A_Constant);
	    System.out.println();
	    System.out.println("--------------------------------");
	    System.out.println("9F1D : Byte-2 = "+Integer.toHexString(TRM_9F1D_B2).toUpperCase());
	    
   } 
	
   
   public static void Byte3() {
	    System.out.println("==================");
	   	System.out.println();
        System.out.println("Kernel Config : "+Kernel_Config);
	    TRM_9F1D_B3=(Kernel_Config & B3_C0_Constant);
	    //System.out.println("Test Value = "+TRM_9F1D_B3);
	    if(TRM_9F1D_B3==0) {
	    System.out.println("9F1D : Byte-3 = 00 ");
	    System.out.println("9F1D = "+Integer.toHexString(TRM_9F1D_B1).toUpperCase()+Integer.toHexString(TRM_9F1D_B2).toUpperCase()+"0000");
	    }else { 
	    System.out.println("9F1D : Byte-3 = "+Integer.toHexString(TRM_9F1D_B3).toUpperCase());  
	    System.out.println("9F1D = "+Integer.toHexString(TRM_9F1D_B1).toUpperCase()+Integer.toHexString(TRM_9F1D_B2).toUpperCase()+Integer.toHexString(TRM_9F1D_B3).toUpperCase()+"00");
	    } 
  } 
	public static void main(String[] args) {
		 				
		TerminalRiskManagementCalculator cu=new TerminalRiskManagementCalculator();
		
		System.out.println("======================= 9F1D Calculation ===================================================");
		System.out.println("1. BYTE-1 Calculation ");
		System.out.println();
		
		cu.Byte1();
		cu.Byte2();
		cu.Byte3();


		
	}	
}
