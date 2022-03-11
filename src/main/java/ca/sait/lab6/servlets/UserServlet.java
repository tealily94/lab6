package ca.sait.lab6.servlets;

import ca.sait.lab6.models.Role;
import ca.sait.lab6.models.User;
import ca.sait.lab6.services.UserService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author soo
 */
public class UserServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService service = new UserService();
        
        try {
            List<User> users = service.getAll();       
            
            request.setAttribute("users", users);
            
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String action = request.getParameter("action");
        if (action != null && action.equals("view")) {
            try {
                String email = request.getParameter("email");
                User user = service.get(email);
                request.setAttribute("selectedEmail", user);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }       
       }            
        this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        UserService service = new UserService();
        
        String action = request.getParameter("action");
        String email = request.getParameter("email");      
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password"); 
        String roleId = request.getParameter("roleId");
        String roleName = "";
        
         try{
             switch (roleId) {
             case "1":
                 roleName = "system admin";
                 break;
             case "2":
                 roleName = "regular user";
                 break;
             case "3":
                 roleName = "company admin";       
           }
        }catch(Exception e) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("message", "error");
        }
         
        Role role = new Role(Integer.parseInt(roleId), roleName);
        
        try{
             switch (action) {
                case "Create":
                   service.insert(email, true, firstName, lastName, password, role);
                   break;
                case "Edit":
                   service.update(email, true, firstName, lastName, password, role);
                    break;
                case "Delete":
                  service.delete(email);
            }
              request.setAttribute("message", action);
        } catch(Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
        
      getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);    
    }

}
