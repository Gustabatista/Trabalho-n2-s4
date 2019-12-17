
package contoller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Livro;
import model.DAO.LivroDAO;

public class LivroController {
    
    private Livro vendedorSelecionado;
    private List<Livro> tabelaDeVendedores;
    private LivroDAO lDAO;
    
    public LivroController() {
        lDAO = new LivroDAO();
    }
    
    public void listarTodos(DefaultTableModel modeloTabela) {
        modeloTabela.setNumRows(0);
        List<Livro> listaLivros = lDAO.buscarTodos();
        

        for (Livro v : listaLivros) {
            modeloTabela.addRow(new Object[]{v.getId(), v.ISBN(),
                v.getTitulo(), v.getAutor(), v.getPaginas(). v.getPreco v.isStatus(),  ? "1 - Ativo" : "2 - Inativo"});
        }
    }

    
    
       
}
