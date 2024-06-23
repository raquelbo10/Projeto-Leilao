/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
       conectaDAO conecta = new conectaDAO();
          conn =  conecta.connectDB();
               PreparedStatement ps = null;
           try{
           ps = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES(?,?,?)");
           ps.setString(1, produto.getNome());
           ps.setInt(2, produto.getValor());
           ps.setString(3, produto.getStatus());
           ps.executeUpdate();
           JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
           conn.close();
           ps.close();
               
           }catch(SQLException ex){
           JOptionPane.showMessageDialog(null, "Erro ao cadastrar."); 
        }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        conectaDAO conecta = new conectaDAO();
          conn =  conecta.connectDB();
               PreparedStatement ps = null;
           try{
           ps = conn.prepareStatement("SELECT*FROM produtos");
           resultset = ps.executeQuery();
           while(resultset.next()){
              ProdutosDTO produto = new ProdutosDTO();
               produto.setId(resultset.getInt("id"));
               produto.setNome(resultset.getString("nome"));
               produto.setValor(resultset.getInt("valor"));
               produto.setStatus(resultset.getString("status"));
               listagem.add(produto);
           }
           return listagem;
           }
           catch(SQLException ex){
                   return null;
                   }
        
    }
    public void venderProdutos(int id){
         conectaDAO conecta = new conectaDAO();
          conn =  conecta.connectDB();
               PreparedStatement ps = null;
           try{
           ps = conn.prepareStatement("UPDATE produtos SET status = ? WHERE id = ?");
           ps.setString(1, "vendido");
           ps.setInt(2, id);
           ps.executeUpdate();
         JOptionPane.showMessageDialog(null, "Produto vendido!"); 
    }
      catch(SQLException ex){
    JOptionPane.showMessageDialog(null, "Erro ao vender produto."); 
        
}

    }
    public List<ProdutosDTO> listarProdutosVendidos(){
        List<ProdutosDTO> lista = new ArrayList<>();
        conectaDAO conecta = new conectaDAO();
          conn =  conecta.connectDB();
               PreparedStatement ps = null;
           try{
           ps = conn.prepareStatement("SELECT FROM produtos WHERE status = vendido");
          
           resultset = ps.executeQuery();
           while(resultset.next()){
              ProdutosDTO produto = new ProdutosDTO();
               produto.setId(resultset.getInt("id"));
               produto.setNome(resultset.getString("nome"));
               produto.setValor(resultset.getInt("valor"));
               produto.setStatus(resultset.getString("status"));
               listagem.add(produto);
           }
        
        return lista;
    }
           catch(SQLException ex){
                   return null;
                   }
}
}
