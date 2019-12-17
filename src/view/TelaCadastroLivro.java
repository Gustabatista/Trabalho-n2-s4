/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import contoller.LivroController;
import contoller.VendedorController;
import javax.swing.event.ListSelectionEvent;
import model.bean.Livro;

/**
 *
 * @author Aluno
 */
public class TelaCadastroLivro extends javax.swing.JInternalFrame {
    private javax.swing.table.DefaultTableModel tabelaModelo;
    private LivroController lController;
    private Livro lSelecionado = new Livro();
    private boolean podeEditar = false;

    /**
     * Creates new form TelaCadastroLivro
     */
    public TelaCadastroLivro() {
       
        lController = new LivroController();
        CriarTabelaModelo();
        initComponents();
        lController.listarTodos(tabelaModelo);
        limparCampos();
    }
    
    public void CriarTabelaModelo() {

        tabelaModelo = new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null, null}
                },
                new String[]{
                    "ISBN", "Titulo", "Autor", "Paginas", "Categorias", "Preco", "Status"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, 
                java.lang.String.class, java.lang.Integer.class, 
                java.lang.String.class, java.lang.Double.class, 
                java.lang.Boolean.class
            };

            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

        };
    }

    public void preencherSelecionado(ListSelectionEvent e) {
        int linha = tabelaDeLivros.getSelectedRow();
        try {
            int id = Integer.parseInt(tabelaModelo.getValueAt(linha, 0).toString());
            String ISBN = tabelaModelo.getValueAt(linha, 1).toString();
            String Titulo = tabelaModelo.getValueAt(linha, 2).toString();
            String Autor = tabelaModelo.getValueAt(linha, 3).toString();
            int Paginas = Integer.parseInt(tabelaModelo.getValueAt(linha, 4).toString());
            String Categorias = tabelaModelo.getValueAt(linha, 5).toString();
            double Preco =Double.parseDouble(tabelaModelo.getValueAt(linha, 6).toString());
            boolean Status = tabelaModelo.getValueAt(linha, 7).toString().equals("Disponível");
            
            

            this.preencherLivro(lSelecionado, id,ISBN, Titulo, Autor, Paginas, Categorias, Preco, Status);

            this.preencherCampos();
            this.habilitarCamposEdicao();
        } catch (Exception erro) {
            this.limparCampos();
        }
    }
    
    public void preencherLivro(Livro l, int id, String ISBN, String Titulo, 
            String Autor, Integer Paginas, String Categorias, Double Preco,Boolean Status) {
        if (ISBN != null && Titulo != null && Autor != null) {
            l.setId(id);
            l.setISBN(0);
            l.setTitulo(Titulo);
            l.setAutor(Autor);
            l.setPaginas(Paginas);
            l.setCategoria(Categorias);
            l.setPreco(0);
            l.setStatus(true);
            
        } else {
            this.limparCampos();
        }
    }

    public void preencherCampos() {
        ISBNtext.setText(String.valueOf(lSelecionado.getISBN()));
        titulotext.setText(lSelecionado.getTitulo());
        autor.setText(lSelecionado.getAutor());
        paginas.setText(String.valueOf(lSelecionado.getPaginas()));
        preco.setText(String.valueOf(lSelecionado.getPreco()));
        categoria.setText(lSelecionado.getCategoria());
        String status_index = lSelecionado.isStatus() ? "1 - Ativo" : "2 - Inativo";
       Status.getModel().setSelectedItem(status_index);
    }

    public void limparCampos() {
        lSelecionado = new Livro(); // Limpar livro selecionado

        ISBNtext.setText("");
        titulotext.setText("");
        autor.setText("");
        paginas.setText("");
        preco.setText("");
        categoria.setText("");
        String status_index = "2 - Inativo";
        Status.getModel().setSelectedItem(status_index);

        tabelaDeLivros.getSelectionModel().clearSelection();
        this.desabilitarCamposEdicao();
    }
    
    public void desabilitarCamposEdicao() {
        this.ISBNtext.setEnabled(false);
        this.titulotext.setEnabled(false);
        this.autor.setEnabled(false);
        this.paginas.setEnabled(false);
        this.preco.setEnabled(false);
        this.categoria.setEnabled(false);
        this.Status.setEnabled(false);
        
        this.podeEditar = false;
    }
    
    public void habilitarCamposEdicao() {
        this.ISBNtext.setEnabled(true);
        this.titulotext.setEnabled(true);
        this.autor.setEnabled(true);
        this.paginas.setEnabled(true);
        this.preco.setEnabled(true);
        this.categoria.setEnabled(true);
        this.Status.setEnabled(true);
        
        this.podeEditar = true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaDeLivros = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        titulotext = new javax.swing.JTextField();
        paginas = new javax.swing.JTextField();
        autor = new javax.swing.JTextField();
        preco = new javax.swing.JTextField();
        categoria = new javax.swing.JTextField();
        ISBNtext = new javax.swing.JTextField();
        Status = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setTitle("Tela Cadastro Livro");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelaDeLivros.setModel(tabelaModelo);
        jScrollPane1.setViewportView(tabelaDeLivros);
        tabelaDeLivros.getTableHeader().setReorderingAllowed(false);
        tabelaDeLivros.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                preencherSelecionado(e);
            }
        });
        getContentPane().add(tabelaDeLivros, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 80));

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 618, 190));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("TÍTULO:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("AUTOR:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("PÁGINAS:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("PREÇO:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("CATEGORIAS:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("STATUS:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("ISBN:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        titulotext.setText("Título do livro");
        getContentPane().add(titulotext, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 202, -1));

        paginas.setText("Quantidade de páginas");
        paginas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paginasActionPerformed(evt);
            }
        });
        getContentPane().add(paginas, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 202, -1));

        autor.setText("Nome do autor");
        autor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autorActionPerformed(evt);
            }
        });
        getContentPane().add(autor, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 202, -1));

        preco.setText("Preço do livro");
        getContentPane().add(preco, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 202, -1));

        categoria.setText("Ex:Terror");
        categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaActionPerformed(evt);
            }
        });
        getContentPane().add(categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 202, -1));

        ISBNtext.setText("0000-0000");
        getContentPane().add(ISBNtext, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 202, -1));

        Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disponível", "Não disponível" }));
        getContentPane().add(Status, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 202, -1));

        jPanel1.setBackground(new java.awt.Color(0, 102, 255));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 450));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Vijaya", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 255));
        jLabel8.setText("Book Store");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        jButton1.setText("SALVAR");
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 99, 69));

        jButton2.setText("NOVO");
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 97, 69));

        jButton3.setText("PROCURAR");
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 99, 70));

        jButton4.setText("EXCLUIR");
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 97, 70));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 300, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoriaActionPerformed

    private void autorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_autorActionPerformed

    private void paginasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paginasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paginasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ISBNtext;
    private javax.swing.JComboBox<String> Status;
    private javax.swing.JTextField autor;
    private javax.swing.JTextField categoria;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField paginas;
    private javax.swing.JTextField preco;
    private javax.swing.JTable tabelaDeLivros;
    private javax.swing.JTextField titulotext;
    // End of variables declaration//GEN-END:variables
}
