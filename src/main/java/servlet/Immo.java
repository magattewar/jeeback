/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.AjoutBailleur;
import model.AjoutBien;
import model.Bailleur;
import model.Bailleur2;
import model.Bien;
import model.Bien1;
import model.Client;
import model.Client1;
import model.Creditbailleur;
import model.Etatbien;
import model.Facture;
import model.Location;
import model.Location2;
import model.Loyer;
import model.ReglementBailleur;
import model.Texte;
import model.User;
import model.User1;
import model.Verifcni;
import services.BailleurFacadeLocal;
import services.BienFacadeLocal;
import services.ClientFacadeLocal;
import services.CreditbailleurFacadeLocal;
import services.LocationFacadeLocal;
import services.LoyerFacadeLocal;
import services.MoyenpaiementFacadeLocal;
import services.UserFacadeLocal;
import utilitaires.Upload;

/**
 *
 * @author magat
 */
@WebServlet(name = "Immo", urlPatterns = {"/immo"})
@MultipartConfig
public class Immo extends HttpServlet {

    @EJB
    private UserFacadeLocal userEJB;
    
    @EJB
    private MoyenpaiementFacadeLocal moyenpEJB;

    @EJB
    private BienFacadeLocal bienEJB;

    @EJB
    private BailleurFacadeLocal bailleurEJB;

    @EJB
    private ClientFacadeLocal clientEJB;

    @EJB
    private LocationFacadeLocal locationEJB;

    @EJB
    private LoyerFacadeLocal loyerEJB;

    @EJB
    private CreditbailleurFacadeLocal creditbailleurEJB;

    protected void updateCB() {
        List<Bien> lb = bienEJB.findAll();
        Date date = Date.from(Instant.now());
        for (Bien bien : lb) {
            //System.out.println(date - bien.getDatedernierpaiement());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Calendar d = Calendar.getInstance();
            c.setTime(date);
            d.setTime(bien.getDatedernierpaiement());
            d.add(Calendar.DAY_OF_MONTH, 30);
            //bien.getDatedernierpaiement().add(Calendar.DAY_OF_MONTH, 7);
            if (c.after(d)) {
                System.out.println("date pas encore atteinte");
            } else {
                //System.out.println("date atteinte");
                Creditbailleur cb = new Creditbailleur();
                cb.setDate(null);
                cb.setIdbien(bien);
                cb.setMontant(bien.getMontantvoulu());

                creditbailleurEJB.create(cb);
            }
        }
    }

    /**
     * Mise a jour des debitClient
     */
    protected void updateDC() {
        List<Location> ll = locationEJB.findAll();
        Date date = Date.from(Instant.now());
        for (Location loc : ll) {
            //System.out.println(date - bien.getDatedernierpaiement());
            if (loc.getOccupe() == true) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                Calendar d = Calendar.getInstance();
                c.setTime(date);
                d.setTime(loc.getDatedernierpaiem());
                d.add(Calendar.DAY_OF_MONTH, 30);
                //bien.getDatedernierpaiement().add(Calendar.DAY_OF_MONTH, 7);
                if (c.after(d)) {
                    System.out.println("date pas encore atteinte");
                } else {
                    //System.out.println("date atteinte");
                    Loyer l = new Loyer();
                    l.setIdlocation(loc);
                    l.setMontant(loc.getMontant());
                    loyerEJB.create(l);
                }
            }

        }
    }

    /**
     * Ajout de la caution apres location
     *
     * @param b
     */
    protected void addCaution(Location b) {
        Loyer l = new Loyer();
        System.out.println("Montant loyer : " + b.getMontant());
        l.setIdlocation(b);
        l.setMontant(b.getMontant() * 2);
        l.setPaye(false);
        l.setDateenregistrement(new Date());
        loyerEJB.create(l);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Immo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Immo at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        //processRequest(request, response);

        String action = "";
        String json;
        Gson gson = new Gson();
        int avant, apres;
        User users = null;
        /**
         * Activer la session
         */
        //HttpSession session = request.getSession(true);

        /**
         * recuperer la valeur de action dans la requete faite par le client
         */
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
            /**
             * Definition du format des donnees a renvoyer
             */
            response.setContentType("application/json");

            /**
             * Obligatoire sinon le client n aura pas le droit d acceder a l api
             */
            response.setHeader("Access-Control-Allow-Origin", "*");
        } else if (request.getServletPath() != null) {
            action = request.getServletPath();
        }

        /**
         * switch sur action pour savoir ce que veut le client
         */
        switch (action) {
            case "login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");

                users = userEJB.login(login, password);
                //System.out.println(users.get(0).getLogin());
                if (users.getId() > 0) {
                    //session.setAttribute("user", users);
                    User1 user = new User1();
                    user.setLogin(login);
                    user.setPassword(password);
                    json = gson.toJson(user);
                    response.getWriter().println(json);
                } else {
                    User1 user = new User1();
                    user.setLogin(login);
                    user.setPassword(password);
                    user.setMatched(false);
                    json = gson.toJson(user);
                    response.getWriter().println(json);
                }
                break;
            case "checkconnected":
                //session = request.getSession(true);
                if (users != null) {
                    //users = (User) session.getAttribute("user");
                    User1 user = new User1();
                    user.setLogin(users.getLogin());
                    user.setPassword(users.getPassword());
                    json = gson.toJson(user);
                    response.getWriter().println(json);
                } else {
                    User1 user = new User1();
                    user.setMatched(false);
                    json = gson.toJson(user);
                    response.getWriter().println(json);
                }
                break;

            case "listebiens":
                System.out.println("We entered ListeBiens action");
                //updateCB();
                List<Bien> biens = bienEJB.findAll();
                List<Bien1> liste = new ArrayList<>();
                for (Bien bien : biens) {
                    Bien1 bien1 = new Bien1();
                    bien1.setId(bien.getId());
                    bien1.setAdresse(bien.getAdresse());
                    bien1.setDescription(bien.getDescription());
                    bien1.setDisponibilite(bien.getDisponibilite());
                    bien1.setMontantvoulu(bien.getMontantvoulu());
                    bien1.setPrixlocation(bien.getPrixlocation());
                    bien1.setCnibailleur(bien.getIdbailleur().getCni() + "");

                    //File file = new File(bien.getPhoto());
                    bien1.setPhoto(bien.getPhoto());
                    bien1.setIdBailleur(bien.getIdbailleur().getId());
                    liste.add(bien1);
                }

                json = gson.toJson(liste);

                response.getWriter().println(json);
                //updateCB();
                //updateDB();
                break;
            case "verifcnibailleur":
                Verifcni cni = new Verifcni(false, 0);
                Bailleur bailleur = bailleurEJB.finByCni(Integer.parseInt(request.getParameter("cni")));
                if (bailleur != null) {
                    cni.setExiste(true);
                    cni.setId(bailleur.getId());
                } else {
                    cni.setExiste(false);
                }
                json = gson.toJson(cni);
                response.getWriter().println(json);
                break;
            case "addbailleur":

                avant = bailleurEJB.count();
                bailleur = new Bailleur();
                bailleur.setCni(Integer.parseInt(request.getParameter("cni")));
                bailleur.setNom(request.getParameter("nom"));
                bailleur.setBienList(null);
                bailleur.setModepaiement(moyenpEJB.find(3));
                bailleurEJB.create(bailleur);
                apres = bailleurEJB.count();
                AjoutBailleur ab = new AjoutBailleur(false);
                if (apres > avant) {
                    ab.setAdded(true);
                }

                response.getWriter().println(gson.toJson(ab));
                break;
            case "addbien":
                avant = bienEJB.count();
                Bien bien = new Bien();
                bien.setDescription(request.getParameter("description"));
                bien.setIdbailleur(bailleurEJB.find(Integer.parseInt(request.getParameter("idbailleur"))));
                bien.setMontantvoulu(Float.parseFloat(request.getParameter("montantvoulu")));
                bien.setPrixlocation(Float.parseFloat(request.getParameter("prixlocation")));
                bien.setPhoto(request.getParameter("photo"));
                bien.setAdresse(request.getParameter("adresse"));
                bien.setDisponibilite(true);

                bienEJB.create(bien);
                apres = bienEJB.count();

                AjoutBien abien = new AjoutBien();
                if (apres > avant) {
                    abien.setReussi(true);
                } else {
                    abien.setReussi(true);
                }

                response.getWriter().println(gson.toJson(abien));

                break;
            case "verifcniclient":
                Verifcni cniClient = new Verifcni(false, 0);
                List<Client> client = clientEJB.getByCni(request.getParameter("cni"));
                if (!client.isEmpty()) {
                    cniClient.setExiste(true);
                    cniClient.setId(client.get(0).getId());
                } else {
                    cniClient.setExiste(false);
                }
                json = gson.toJson(cniClient);
                response.getWriter().println(json);

                break;

            case "addclient":
                response.setHeader("Access-Control-Allow-Origin", "*");
                avant = clientEJB.count();
                Client clientadd = new Client();
                clientadd.setCni(request.getParameter("cni"));
                clientadd.setNom(request.getParameter("nom"));
                clientadd.setTelephone(request.getParameter("telephone"));
                clientEJB.create(clientadd);
                apres = clientEJB.count();

                AjoutBailleur abcl = new AjoutBailleur(true);
                if (apres > avant) {
                    abcl.setAdded(true);
                } else {
                    abcl.setAdded(false);
                }
                response.getWriter().println(gson.toJson(abcl));
                break;
            case "louer":
                System.out.println("location..............");
                avant = locationEJB.count();
                Location loc = new Location();
//                System.out.println("id bien " + Integer.parseInt(request.getParameter("idbien")) + "id client "
//                        + Integer.parseInt(request.getParameter("idclient")));
                String etatbien = request.getParameter("etatbien");
                int idbien = Integer.parseInt(request.getParameter("idbien"));
                int idclient = Integer.parseInt(request.getParameter("idclient"));
                loc.setIdbien(bienEJB.find(idbien));
                loc.setIdclient(clientEJB.find(idclient));
                loc.setMontant(bienEJB.find(idbien).getPrixlocation());
                //System.out.println("Montant location : " + bienEJB.find(Integer.parseInt(request.getParameter("idbien"))).getPrixlocation());
                loc.setOccupe(true);
                loc.setDatedernierpaiem(Date.from(Instant.now()));
                loc.setDateenregistrement(Date.from(Instant.now()));
                loc.setEtatbien(etatbien);
                locationEJB.create(loc);

                apres = locationEJB.count();
                AjoutBailleur abloc = new AjoutBailleur(true);
                if (apres > avant) {

                    abloc.setAdded(true);
                    Location loc1 = locationEJB.findLast(loc.getDateenregistrement());
                    //System.out.println("Montant Location : "+loc1.getMontant());
                    addCaution(loc1);
                    //bienEJB.updateOccupation(Integer.parseInt(request.getParameter("idbien")), false);
                    Bien bienUpdate = bienEJB.find(idbien);
                    bienUpdate.setDisponibilite(false);
                    bienEJB.edit(bienUpdate);
                    bienEJB.updateOccupation(idbien, false);
                    //addCaution(loc);
                }

                response.getWriter().println(gson.toJson(abloc));
                break;

            case "getdetails":
                Texte txt = new Texte("");
                int id = Integer.parseInt(request.getParameter("id"));
                Bien biend = bienEJB.find(id);
                String chemin = "F://javaDev/M1GLImmo/src/main/webapp/images/" + biend.getPhoto();
                txt.setLibelle(chemin + biend.getPhoto());
                Bien1 bien1 = new Bien1();
                bien1.setId(biend.getId());
                bien1.setAdresse(biend.getAdresse());
                bien1.setDescription(biend.getDescription());
                bien1.setDisponibilite(biend.getDisponibilite());
                bien1.setMontantvoulu(biend.getMontantvoulu());
                bien1.setPrixlocation(biend.getPrixlocation());
                bien1.setPhoto(chemin);
                bien1.setIdBailleur(biend.getIdbailleur().getId());
                File myfile = new File(chemin);
                System.out.println("fichier : " + myfile.toString());
                response.getWriter().println(gson.toJson(bien1));

                break;

            case "listebailleurs":

                List<Bailleur> bailleurs = bailleurEJB.findAll();
                List<Bailleur2> listeb = new ArrayList<>();
                for (Bailleur bailleur1 : bailleurs) {
                    Bailleur2 bailleur2 = new Bailleur2();
                    bailleur2.setCni(bailleur1.getCni() + "");
                    bailleur2.setNom(bailleur1.getNom());
                    bailleur2.setId(bailleur1.getId());
                    bailleur2.setNombreproprietes(bailleur1.getBienList().size());
                    bailleur2.setIdmodepaiemment(bailleur1.getModepaiement().getId());
                    bailleur2.setModepaiement(bailleur1.getModepaiement().getLibelle());
                    float cdb = 0;
//                    for (Float cdbr : bailleurEJB.credit(bailleur1.getId())) {
//                        cdb += cdbr;
//                    }
//                    bailleur2.setCredit(cdb);
                    //System.out.println(bailleurEJB.credit(1));
                    listeb.add(bailleur2);
                }

                json = gson.toJson(listeb);
                response.getWriter().println(json);

                break;

            case "listeclients":
                List<Client> listec = clientEJB.findAll();
                List<Client1> listec1 = new ArrayList<>();
                for (Client c : listec) {
                    Client1 c1 = new Client1();
                    c1.setId(c.getId());
                    c1.setNom(c.getNom());
                    c1.setCni(c.getCni());
                    c1.setTelephone(c.getTelephone());
                    c1.setMontantdu(loyerEJB.getMontantDu(c1.getId()));
                    //System.out.println("Montant du      :       "+loyerEJB.getMontantDu(c1.getId()));
                    listec1.add(c1);
                }
                json = gson.toJson(listec1);
                response.getWriter().println(json);

                break;

            case "facturer":
                int idClient = Integer.parseInt(request.getParameter("id"));
                Client cli = clientEJB.find(idClient);
                Facture facture = new Facture();
                facture.setCni(cli.getCni());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

                facture.setDateFacturation(simpleDateFormat.format(new Date()));
                facture.setNom(cli.getNom());
                facture.setType("Mensualité");
                facture.setMontant(loyerEJB.getMontantDu(idClient));
                facture.setTelephone(cli.getTelephone());
                response.getWriter().println(gson.toJson(facture));
                break;

            case "reglerbailleur":
                int idBailleur = Integer.parseInt(request.getParameter("idbailleur"));
                ReglementBailleur rb = new ReglementBailleur();
                //rb.setRegle(bailleurEJB.regler(idBailleur));

                List<Bien> lb = bienEJB.listeByBailleur(idBailleur);
                for (Bien bien2 : lb) {
                    //rb.setRegle(bailleurEJB.regler(bien2.getId()));
                    //System.out.println("bien ::: "+ bien2.toString());
                    List<Creditbailleur> cb = creditbailleurEJB.findByBien(bien2.getId());
                    for (Creditbailleur creditbailleur : cb) {
                        creditbailleur.setPaye(true);
                        System.out.println("credit bailleur   :   " + cb.toString());
                        creditbailleurEJB.edit(creditbailleur);
                        rb.setRegle(true);
                    }

                }
                response.getWriter().println(gson.toJson(rb));
                break;

            case "reglerClient":
                int idClent = Integer.parseInt(request.getParameter("idClient"));
                ReglementBailleur rb1 = new ReglementBailleur();
                List<Loyer> ll = loyerEJB.listeByClient(idClent);

                for (Loyer loyer : ll) {
                    loyer.setPaye(true);
                    loyerEJB.edit(loyer);
                }

                response.getWriter().println(gson.toJson(rb1));
                break;

            case "listeLocations":

                List<Location> listloc = locationEJB.findAll();

                List<Location2> ll2 = new ArrayList<>();
                for (Location loc2 : listloc) {
                    Location2 ll3 = new Location2();
                    ll3.setId(loc2.getId());
                    ll3.setEtatbien(loc2.getEtatbien());
                    ll3.setIdbien(loc2.getIdbien().getId());
                    ll3.setIdclient(loc2.getIdclient().getId());
                    ll3.setMontant(loc2.getMontant());
                    ll3.setOccupe(loc2.getOccupe());
                    ll2.add(ll3);
                }

                response.getWriter().println(gson.toJson(ll2));

                break;

            case "changeretat":

                int idLocation = Integer.parseInt(request.getParameter("idlocation"));
                String etaT = request.getParameter("etatbien");

                Location location = locationEJB.find(idLocation);
                location.setEtatbien(etaT);

                locationEJB.edit(location);

//                bien = bienEJB.find(location.getIdbien().getId());
//                bien.setDisponibilite(true);
//                bienEJB.edit(bien);
                response.getWriter().println("changed : true");
                break;

            case "liberer":
                   
                try {
                
                    idLocation = Integer.parseInt(request.getParameter("idlocation"));
                //etaT = request.getParameter("etatbien");

                Location location2 = locationEJB.find(idLocation);
//                
               // locationEJB.liberer(idLocation);
//                
                location2.setOccupe(false);
//                //location.setEtatbien(etaT);
                locationEJB.edit(location2);

                Bien bien2 = bienEJB.find(location2.getIdbien().getId());
                    System.out.println(bien2.getDisponibilite() +"   id du bien a modifier  :  "+ location2.getIdbien().getId());
//                
//                //bienEJB.changerDispo(location.getIdbien().getId());
//                
                bien2.setDisponibilite(true);
                bienEJB.edit(bien2);

                response.getWriter().println("changed : true");
                    
                } catch (Exception e) {
                    response.getWriter().println("changed : false");
                }
                
                
                break;

            case "updatecb":

                updateCB();

                break;

            case "updatedc":

                updateCB();

                break;

            default:
                //json = gson.toJson("Nothing to be done");
                response.getWriter().println("Nothing to be done");
                break;

        }

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
        processRequest(request, response);

        String action = (request.getParameter("action") != null ? request.getParameter("action") : "");
        switch (action) {
            case "addbien":
                //System.out.println("Doget Addbien Method");
                int avant,
                 apres;
                Gson gson = new Gson();
                response.setContentType("application/json");

                /**
                 * Obligatoire sinon le client n aura pas le droit d acceder a l
                 * api
                 */
                response.setHeader("Access-Control-Allow-Origin", "*");
                avant = bienEJB.count();
                Bien bien = new Bien();
                bien.setDescription(request.getParameter("description"));
                bien.setIdbailleur(bailleurEJB.find(Integer.parseInt(request.getParameter("idbailleur"))));
                bien.setMontantvoulu(Float.parseFloat(request.getParameter("montantvoulu")));
                bien.setPrixlocation(Float.parseFloat(request.getParameter("prixlocation")));
                bien.setLocationList(null);
                bien.setEtatbienList(null);
                bien.setCreditbailleurList(null);
               
                Part img = request.getPart("photo");
                //bien.setPhoto("bff52a1c-2e07-4148-8051-53e375f9a1ff.jpg");
                bien.setAdresse(request.getParameter("adresse"));
                bien.setDisponibilite(true);
                bien.setDateenregistrement(new Date());
                //bienEJB.create(bien);
                try {
                    InputStream ips = img.getInputStream();
                    String chemin = "C://Users/magat/Documents/NetBeansProjects/mavenproject1/ProjetJEEBack/src/main/webapp/images/";
                    String filePath = chemin + (avant + 1) + img.getSubmittedFileName();
                    InputStream stream = img.getInputStream();
                    Upload.saveFile(stream, filePath);
                    bien.setPhoto((avant + 1) + img.getSubmittedFileName());
                    bienEJB.create(bien);
                } catch (Exception e) {
                }
                apres = bienEJB.count();
                AjoutBien abien = new AjoutBien();
                Texte txt = new Texte("");
                if (apres > avant) {
                    abien.setReussi(true);
                } else {
                    abien.setReussi(false);
                    //txt.setLibelle(img.getSubmittedFileName());
                }

                response.getWriter().println(gson.toJson(abien));

                break;
            case "modifbien":
                
                response.setHeader("Access-Control-Allow-Origin", "*");
                int id = Integer.parseInt(request.getParameter("idmodif"));
                Bien bienmod = bienEJB.find(id);
                bienmod.setDescription(request.getParameter("description"));
                //adresse
                bienmod.setAdresse(request.getParameter("adresse"));
                bienmod.setPrixlocation(Float.parseFloat(request.getParameter("prixlocation")));
                bienmod.setMontantvoulu(Float.parseFloat(request.getParameter("montantvoulu")));
                bienEJB.edit(bienmod);
                
                

                
                break;
            default:
                break;

        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
