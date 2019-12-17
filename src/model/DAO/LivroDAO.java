
package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Livro;
import DB.Connect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LivroDAO implements iDAO<Livro> {
    
    private final String INSERT = "INSERT INTO livro(ISBN, LIVRO, AUTOR, PAGINAS, CATEGORIAS, PREÇO, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE = "UPDATE vendedor SET ISBN=?, NOME=?, AUTOR=?, PAGINAS=?, CATEGORIAS=?, PREÇO=?, STATUS=? WHERE ID =?";
    private final String DELETE = "DELETE FROM vendedor WHERE ID =?";
    private final String LISTALL = "SELECT * FROM livro";
    private final String LISTBYID = "SELECT * FROM livro WHERE ID=?";
    private final String LISTBYISBN = "SELECT * FROM livro WHERE ISBN=?";
    
    private Connect conn = null;
    private Connection conexao = null;
    
    @Override
    public Livro inserir(Livro novoLivro) {
        conexao = this.getConnect().connection;
        if (novoLivro != null && conexao != null) {
            try {
                PreparedStatement transacaoSQL;
                transacaoSQL = conexao.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

                transacaoSQL.setInt(1, novoLivro.getISBN());
                transacaoSQL.setString(2, novoLivro.getTitulo());
                transacaoSQL.setString(3, novoLivro.getAutor());
                transacaoSQL.setDouble(4, novoLivro.getPaginas());
                transacaoSQL.setString(5, novoLivro.getCategoria());
                transacaoSQL.setDouble(6, novoLivro.getPreco());
                transacaoSQL.setBoolean(7, novoLivro.isStatus());
                
                

                transacaoSQL.execute();
                JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso", "Registro inserido", JOptionPane.INFORMATION_MESSAGE);

                try (ResultSet generatedKeys = transacaoSQL.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        novoLivro.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Não foi possível recuperar o ID.");
                    }
                }

                conn.fechaConexao(conexao, transacaoSQL);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir o livro no banco de" + "dados. \n" + e.getMessage(), "Erro na transação SQL", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Os dados do livro não podem estar vazios.", "Livro não informado", JOptionPane.ERROR_MESSAGE);
        }

        return novoLivro;
    }
    
    @Override
    public Livro atualizar(Livro livroEditado) {
        conexao = this.getConnect().connection;
        if (livroEditado != null && conexao != null) {
            try {
                PreparedStatement transacaoSQL;
                transacaoSQL = conexao.prepareStatement(UPDATE);

                 transacaoSQL.setInt(1, livroEditado.getISBN());
                transacaoSQL.setString(2, livroEditado.getTitulo());
                transacaoSQL.setString(3, livroEditado.getAutor());
                transacaoSQL.setDouble(4, livroEditado.getPaginas());
                transacaoSQL.setString(5, livroEditado.getCategoria());
                transacaoSQL.setDouble(6, livroEditado.getPreco());
                transacaoSQL.setBoolean(7, livroEditado.isStatus());
                
                transacaoSQL.setInt(5, livroEditado.getId());

                int resultado = transacaoSQL.executeUpdate();

                if (resultado == 0) {
                    JOptionPane.showMessageDialog(null, "Não foi possível atualizar as informações", "Erro ao atualizar", JOptionPane.ERROR_MESSAGE);
                    throw new SQLException("Creating user failed, no rows affected.");
                }

                JOptionPane.showMessageDialog(null, "Livro atualizado com sucesso", "Registro atualizado", JOptionPane.INFORMATION_MESSAGE);

                conn.fechaConexao(conexao, transacaoSQL);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir o livro no banco de" + "dados. \n" + e.getMessage(), "Erro na transação SQL", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Os dados do livro não podem estar vazios.", "Vendedor não informado", JOptionPane.ERROR_MESSAGE);
        }

        return livroEditado;
    }
    
    @Override
    public void excluir(int idLivro) {
        
        int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este livro?", "Confirmar exclusão",
			JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        // 0 - Sim  1 - Não
        if(confirmar == 1) {
            return;
        }
        conexao = this.getConnect().connection;
        if (conexao != null) {
            try {
                PreparedStatement transacaoSQL;
                transacaoSQL = conexao.prepareStatement(DELETE);

                transacaoSQL.setInt(1, idLivro);

                boolean erroAoExcluir = transacaoSQL.execute();

                if (erroAoExcluir) {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir", "Não foi possível excluir as informações", JOptionPane.ERROR_MESSAGE);
                    throw new SQLException("Creating user failed, no rows affected.");
                }

                JOptionPane.showMessageDialog(null, "Registro excluido", "Livro excluido com sucesso", JOptionPane.INFORMATION_MESSAGE);

                conn.fechaConexao(conexao, transacaoSQL);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro na transação SQL", "Erro ao excluir do livro no banco de" + "dados. \n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Problemas de conexão", "Não foi possível se conectar ao banco.", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public Livro buscarPorId(int id) {
        conexao = this.getConnect().connection;
        
        ResultSet resultado = null;
        
        Livro livroEncontrado = new Livro();

        if (conexao != null) {
            try {
                PreparedStatement transacaoSQL;
                transacaoSQL = conexao.prepareStatement(LISTBYID);
                transacaoSQL.setInt(1, id);

                resultado = transacaoSQL.executeQuery();

                while (resultado.next()) {
                  
                    
                    livroEncontrado.setId(resultado.getInt("id"));
                    livroEncontrado.setISBN(resultado.getInt("ISBN"));
                    livroEncontrado.setTitulo(resultado.getString("Titulo"));
                    livroEncontrado.setAutor(resultado.getString("Autor"));
                    livroEncontrado.setPaginas(resultado.getInt("Paginas"));
                    livroEncontrado.setCategoria(resultado.getString("Categorias"));
                    livroEncontrado.setPreco(resultado.getDouble("Preco"));

                }
                
                conn.fechaConexao(conexao, transacaoSQL);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro na transação SQL", "Erro ao procurar livro no banco de" + "dados. \n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Problemas de conexão", "Não foi possível se conectar ao banco.", JOptionPane.ERROR_MESSAGE);
        }

        return livroEncontrado;
    }
    
    public Connect getConnect(){
        this.conn =  new Connect ("root", "", "NovoLivro");
        return this.conn;
    }
    
    public Livro buscarPorISBN(int isbn) {
        conexao = this.getConnect().connection;
        
        ResultSet resultado = null;
        Livro livroEncontrado = new Livro();

        if (conexao != null) {
            try {
                PreparedStatement transacaoSQL;
                transacaoSQL = conexao.prepareStatement(LISTBYISBN);
                transacaoSQL.setInt(1, isbn);

                resultado = transacaoSQL.executeQuery();

                while (resultado.next()) {

                    livroEncontrado.setId(resultado.getInt("id"));
                    livroEncontrado.setISBN(resultado.getInt("ISBN"));
                    livroEncontrado.setTitulo(resultado.getString("Titulo"));
                    livroEncontrado.setAutor(resultado.getString("Autor"));
                    livroEncontrado.setPaginas(resultado.getInt("Paginas"));
                    livroEncontrado.setCategoria(resultado.getString("Categoria"));
                    livroEncontrado.setStatus(resultado.getBoolean("Status"));
                    
                }
                
                conn.fechaConexao(conexao, transacaoSQL);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro na transação SQL", "Erro ao procurar vendedor no banco de" + "dados. \n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Problemas de conexão", "Não foi possível se conectar ao banco.", JOptionPane.ERROR_MESSAGE);
        }

        return livroEncontrado;
    }

    @Override
    public List<Livro> buscarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
