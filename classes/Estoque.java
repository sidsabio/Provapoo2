import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class Estoque {
    private List<Produto> produtos = new ArrayList<Produto>();

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void incluirProduto(Produto produto) {
        this.produtos.add(produto);

    }

    public List<Produto> consultarProdutos(String nome) {
        return this.produtos.stream().filter((produto) -> produto.getNome().equals(nome)).collect(Collectors.toList());
    }

    
  

    public Produto consultarProduto(int codigo) {
        Produto produto;
        for (int i = 0; i < this.produtos.size(); i++) {
            if (produtos.get(i).getCodigo() == codigo) {
                produto = produtos.get(i);
                produtos.remove(i);
                return produto;
            }

        }
        return null;

    }

    public double calMaiorValor() {
        double maiorValor = 0;
        for (int i = 0; i < this.produtos.size(); i++) {
            if (this.produtos.get(i).getValor() > maiorValor) {
                maiorValor = this.produtos.get(i).getValor();
            }
        }
        return maiorValor;
    }

    public double calMenorValor() {
        double menorValor = 0;
        for (int i = 0; i < this.produtos.size(); i++) {
            if (this.produtos.get(i).getValor() < menorValor) {
                menorValor = this.produtos.get(i).getValor();
            }
        }
        return menorValor;
    }

    public double calMediaValor() {
        double valorTotal = 0;
        for (int i = 0; i < this.produtos.size(); i++) {
            valorTotal += this.produtos.get(i).getValor();
        }
        return valorTotal / this.produtos.size();

    }
}
