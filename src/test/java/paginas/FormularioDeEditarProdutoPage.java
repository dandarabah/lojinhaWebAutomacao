package paginas;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioDeEditarProdutoPage {
    private WebDriver navegador;

    public FormularioDeEditarProdutoPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public FormularioDeEditarProdutoPage informarEditarNomeDoProduto(String produtoNome) {
        navegador.findElement(By.id("produtonome")).clear();
        navegador.findElement(By.id("produtonome")).sendKeys(produtoNome);
        return this;
    }

    public FormularioDeEditarProdutoPage informarEditarValorDoProduto(String produtoValor) {
        navegador.findElement(By.name("produtovalor")).clear();
        navegador.findElement(By.name("produtovalor")).sendKeys(produtoValor);
        return this;
    }

    public FormularioDeEditarProdutoPage informarEditarCoresDoProduto(String produtoCores) {
        navegador.findElement(By.id("produtocores")).clear();
        navegador.findElement(By.id("produtocores")).sendKeys(produtoCores);
        return this;
    }

    public FormularioAdicionarComponentePage acessarFormularioAdicionarNovoComponenteProduto(){
        navegador.findElement(By.cssSelector("body > div:nth-child(3) > div > div > div.col.m7.s12 > div:nth-child(2) > a")).click();
        return new FormularioAdicionarComponentePage(navegador);
    }

    public FormularioDeEditarProdutoPage submeterFormularioEdicao(){
        navegador.findElement(By.xpath("/html/body/script[5]")).click();
        return this;
    }

    public ListaDeProdutosPage submeterListaDeProdutoEditada(){
        navegador.findElement(By.cssSelector("button[name='action']")).click();
        return new ListaDeProdutosPage(navegador);
    }

}
