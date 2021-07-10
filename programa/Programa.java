import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Programa {
    public static void main(String[] args) throws IOException {
        Estoque estoqueDeProdutos = new Estoque();
        List<Venda> vendas = new ArrayList<Venda>();
        Scanner sc = new Scanner(System.in);
        int opcao;
        System.out.flush();
        do {
            System.out.println("===============MENU=================\n" + "====================================");
            System.out.println("1 – Incluir produto\n" + "==================================");
            System.out.println("2 – Consultar produto\n" + "==================================");
            System.out.println("3 – Listagem de produtos\n" + "==================================");
            System.out.println("4 – Vendas por período – detalhado\n" + "===============================");
            System.out.println("5 – Realizar venda\n" + "==================================");
            System.out.println("0 – Sair\n" + "==================================");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("===============CADASTRO DE  PRODUTO=================");

                    Produto novoProduto = new Produto();
                    System.out.println("==============================\n" + "Código do produto: ");
                    novoProduto.setCodigo(sc.nextInt());
                    sc.nextLine();
                    System.out.println("==============================\n" + "Nome do produto: ");
                    novoProduto.setNome(sc.nextLine());
                    System.out.println("==============================\n" + "Valor do produto: ");
                    novoProduto.setValor(sc.nextDouble());
                    System.out.println("==============================\n" + "Quantidade em estoque: ");
                    novoProduto.setQtdEstoque(sc.nextInt());
                    System.out.println("==============================");
                    estoqueDeProdutos.incluirProduto(novoProduto);
                    System.out.println("===============PRODUTO CADASTADO COM SUCESSO!=================");

                    break;
                case 2:
                    sc.nextLine();
                    System.out.print("Nome do produto: ");
                    List<Produto> local = estoqueDeProdutos.consultarProdutos(sc.nextLine());

                    if (local.size() == 0) {
                        System.out.println("Busca sem resultado");
                        break;
                    }
                    System.out.println("==CÓDIGO======NOME===================VALOR R$=======QUANTIDADE");

                    for (int i = 0; i < local.size(); i++) {
                        System.out.print("    " + local.get(i).getCodigo() + "        ");
                        System.out.print(local.get(i).getNome() + "                    ");
                        System.out.print("R$" + local.get(i).getValor() + " ");
                        System.out.println("         " + local.get(i).getQtdEstoque());

                    }
                    System.out.println("==============================================================");
                    break;
                case 3:
                    List<Produto> local2 = estoqueDeProdutos.getProdutos();
                    if (local2.size() == 0) {
                        System.out.println("Busca sem resultado");
                        break;
                    }
                    System.out.println("==CÓDIGO======NOME===================VALOR R$=======QUANTIDADE");

                    for (int i = 0; i < local2.size(); i++) {
                        System.out.print("    " + local2.get(i).getCodigo() + "        ");
                        System.out.print(local2.get(i).getNome() + "                    ");
                        System.out.print("R$" + local2.get(i).getValor() + " ");
                        System.out.println("         " + local2.get(i).getQtdEstoque());

                    }
                    System.out.println("MAIOR VALOR R$=======MENOR VALOR R$========MÉDIA R$");
                    System.out.print("R$" + estoqueDeProdutos.calMaiorValor() + "                ");
                    System.out.print("R$" + estoqueDeProdutos.calMenorValor() + "                ");
                    System.out.println("R$" + estoqueDeProdutos.calMediaValor());

                    break;
                case 4:
                    System.out.println("Digite o intervalo de consulta:");
                    sc.nextLine();
                    System.out.println("Digite data Inicial (dd/mm/yyyy)");
                    String dataInicial = sc.nextLine();

                    System.out.println("Digite data Final (dd/mm/yyyy)");
                    String dataFinal = sc.nextLine();

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                    Date dateIncial;
                    Date dateFinal;

                    try {
                        dateIncial = formatter.parse(dataInicial);
                        dateFinal = formatter.parse(dataFinal);

                    } catch (ParseException e) {
                        System.out.println("Data invalida inserida, cancelando processo de consulta");
                        break;
                    }

                    List<Venda> vendasDoPeriodo = vendas.stream()
                            .filter((venda) -> venda.getData().compareTo(dateIncial) >= 0
                                    && venda.getData().compareTo(dateFinal) <= 0)
                            .collect(Collectors.toList());

                    if (vendasDoPeriodo.size() == 0) {
                        System.out.println("Busca sem resultado");
                        break;
                    }

                    for (int i = 0; i < vendasDoPeriodo.size(); i++) {
                        System.out.println("DATA DA VENDA========PRODUTO VENDIDO=========QUANTIDADE VENDIDA");
                        System.out.print(formatter.format(vendasDoPeriodo.get(i).getData()) + "\t\t");
                        System.out.print(vendasDoPeriodo.get(i).getProduto().getNome() + "\t\t\t");
                        System.out.println(vendasDoPeriodo.get(i).getQtdVendida() + "\t");

                    }

                    break;
                case 5:
                    System.out.println("Digite o codigo do produto:");
                    Produto produtoDaVenda = estoqueDeProdutos.consultarProduto(sc.nextInt());

                    if (produtoDaVenda == null) {
                        System.out.println("Busca sem resultado, cancelando processo de cadrastro.");
                        break;
                    }
                    System.out.println("Digite o a quantidade vendida do produto:");
                    int quantidadeVendida = sc.nextInt();
                    if (produtoDaVenda.getQtdEstoque() < quantidadeVendida) {
                        System.out.println(
                                "Quantidade vendida maior que a do estoque, cancelando processo de cadrastro.");
                        break;

                    }

                    System.out.println("==============================\n" + "Data da venda : (dd/MM/yyyy)");

                    SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
                    sc.nextLine();
                    String data = sc.nextLine();
                    Date date;
                    try {
                        date = formatter2.parse(data);
                    } catch (ParseException e) {
                        System.out.println("Data invalida inserida, cancelando processo de cadrastro.");
                        break;
                    }
                    Venda venda = new Venda();
                    venda.setData(date);
                    venda.setQtdVendida(quantidadeVendida);
                    venda.setProduto(produtoDaVenda);

                    produtoDaVenda.setQtdEstoque(produtoDaVenda.getQtdEstoque() - quantidadeVendida);
                    estoqueDeProdutos.incluirProduto(produtoDaVenda);
                    vendas.add(venda);

                    break;
                default:
                    break;
            }

        } while (opcao != 0);

    }

}
