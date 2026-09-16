import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in).useLocale(Locale.US);
        Calculadora calc = new Calculadora();

        System.out.println("""
                ===========================================
                            Calculadora simples
                ===========================================""");

        double num1 = lerNumero(scan, "Digite o primeiro valor: ");
        char operacao = lerOperacao(scan);
        double num2 = lerNumero(scan, "Digite o segundo valor: ");

        try {
            double resultado = switch (operacao) {
                case '+' -> calc.somar(num1, num2);
                case '-' -> calc.subtrair(num1, num2);
                case '*' -> calc.multiplicar(num1, num2);
                case '/' -> calc.dividir(num1, num2);
                default -> throw new IllegalStateException("Operação inesperada: " + operacao);
            };
            System.out.printf("%nResultado: %.2f%n", resultado);

        } catch (ArithmeticException e) {
            System.out.printf("%n%s%n", e.getMessage());
        } finally {
            scan.close();
        }
    }

    private static double lerNumero(Scanner scan, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return scan.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("ERRO: Digite um número válido! Tente novamente.\n");
                scan.next();
            }
        }
    }

    private static char lerOperacao(Scanner scan) {
        while (true) {
            System.out.print("Informe a operação (+, -, *, /): ");
            char op = scan.next().charAt(0);

            if (op == '+' || op == '-' || op == '*' || op == '/') {
                return op;
            }
            System.out.println("ERRO: Operação inválida. Escolha +, -, * ou /.\n");
        }
    }
}