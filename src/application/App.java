package application;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.OnlinePaymentService;
import model.services.PaypalService;

public class App {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        System.out.println("Entre os dados do contrato:");
        System.out.print("Numero: ");
        int number = sc.nextInt();
        System.out.print("Date (dd/MM/yyyy): ");
        sc.nextLine();
        LocalDate date = LocalDate.parse(sc.next(), fmt);
        sc.nextLine();
        System.out.print("Valor do contrato: ");
        Double totalValue = sc.nextDouble();

        Contract contract = new Contract(number, date, totalValue);

        System.out.print("Entre com o número de parcelas: ");
        int n = sc.nextInt(); 
       
        double amount = contract.getTotalvalue();

        ContractService contractService = new ContractService(new PaypalService());
              
        contractService.processContract(contract, n);
       
        System.out.println();
        System.out.println("PARCELAS: ");
        for (Installment installment : contract.getInstallments()) {
            System.out.println(installment);    
        }

        sc.close();

    }
}
