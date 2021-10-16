package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

@Controller
@RequestMapping("/")
public class TemplateController {
    private static final String REDIRECT = "redirect:";
    public static final String BBVA_REGISTER = "/bbva-register";
    public static final String LOGIN = "/login/";

    @Autowired
    private ServletContext context;

    /*@Autowired
    private WebClient.Builder webClientBuilder;*/

    @GetMapping("login")
    public String getLoginView(HttpServletRequest request) throws MalformedURLException, UnknownHostException {
        /*System.out.println("---Getting access to entryTest SMAccessController "+request.getServerName() +   request.getServerPort()+   request.getRequestURI()  );
        System.out.println("---request.getRequestURI(): "+request.getRequestURI());*/
        //System.out.println("---rºequest.getRequestURI().getHost: "+new URL(request.getRequestURL().toString()).getHost());
        InetAddress ip = InetAddress.getLocalHost();
        String hostname = ip.getHostName();
        //System.out.println("Your current IP address : " + ip+"\n");
        //System.out.println("Your current Hostname : " + hostname);
        String scheme = request.getScheme();
        String domain = request.getServerName();
        int port = request.getServerPort();
        /*System.out.println("Your scheme : " + scheme+"\n");
        System.out.println("Your domain : " + domain+"\n");
        System.out.println("Your port : " + port+"\n");*/
        System.out.println("Your all : " + scheme+"://"+domain+":"+port+request.getRequestURI()  +"\n");
        // Get client's IP address
        // Get client's host name
        /*String clintHost = request.getLocalName();
        System.out.println("Your getLocalName : " + clintHost+"\n");
        String p = request.getRequestURI();
        String cp = request.getServletContext().getContextPath();

        if (p.startsWith(cp)) {
            System.out.println("=>"+p.substring(cp.length()));
        }
        System.out.println("Your getRequestURI : " + request.getRequestURI()+"\n");*/

        //System.out.println("0path: "+request.getContextPath() +request.getServletContext());

        //System.out.println("1path: "+context.getRealPath("/"));
        /*String absolutePath = context.getRealPath("courses");
        System.out.println("=>  Path: "+ absolutePath);*/
        System.out.println("request.getLocalName(): "+request.getLocalName());
        final String baseUrl =
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        System.out.println("baseUrl: "+baseUrl);

        /////String scheme = request.getScheme() +"://"+request.getServerName()+":"+request.getServerPort()+request.getRequestURI().substring(0, request.getRequestURI().lastIndexOf("/")) +"/login/test/";
        ////System.out.println("===> "+scheme+getQueryParams(sMAccess) );
        return "login";
    }

    @GetMapping("courses")
    public String getCourses(){
        return "courses";
    }

    /*Saber Server Path and Port*/

    /***
     * Example to call get other Request at the same Application
     * return webClientBuilder.build().get().uri(getServerPath() +"/login/test/"
     *                 +getQueryParams(sMAccess)
     *         ).retrieve().bodyToMono(SMAccess.class);
     * @return
     */
    private static String getServerPath(){
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }

    /*@GetMapping(BBVA_REGISTER)
    public Mono<SMAccess> bbvaRegister(HttpServletRequest request, @Valid final @RequestParam String timestamp,
                                       @Valid final @RequestParam OperatingCountry country,
                                       @Valid final @RequestParam(value = "channel_id") Integer channelId,
                                       @Valid final @RequestParam String token,
                                       @Valid final @RequestParam String products,
                                       @Valid final @RequestParam(value = "client_id") String clientId,
                                       @Valid final @RequestParam String destination) throws BusinessException {
        log.info("-SMAccessController.smMpos{} ");
        SMAccess sMAccess = getSMAccess(timestamp, country, channelId, token, products, clientId, destination);
        if (!this.encryptorBusiness.enshureData(sMAccess)) {
            ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(sMAccess);
        }
        //->Falta Agregar guardar en collection nueva
        //->Generar token jwt con el workflow y el bbvaId.

        return webClientBuilder.build().get().uri(getServerPath() +"/login/test/"
                +getQueryParams(sMAccess)
        ).retrieve().bodyToMono(SMAccess.class);
    }*/

    /*private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientResponse -> {
            log.info("Request {} {}", clientResponse.method(), clientResponse.url());
            return Mono.just(clientResponse);
        });
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            log.info("Response status code {} ", clientResponse.statusCode());
            return Mono.just(clientResponse);
        });
    }*/
    /*private String getQueryParams(SMAccess sMAccess){
        return "?workflow="+this.apiGatewayBusiness.getMposCountry(sMAccess)
                +"&timestamp="+sMAccess.getTimestamp()
                +"&country="+sMAccess.getCountry()
                +"&channel_id="+sMAccess.getChannelId()
                +"&token="+sMAccess.getToken()
                +"&products="+sMAccess.getProducts()
                +"&client_id="+sMAccess.getClientId()
                +"&destination="+sMAccess.getDestination();
    }

    @GetMapping(LOGIN)
    public ModelAndView smEntry(final ModelMap model, @Valid final @RequestParam String workflow,
                                final @RequestParam String timestamp,
                                final @RequestParam OperatingCountry country,
                                final @RequestParam(value = "channel_id") Integer channelId,
                                final @RequestParam String token,
                                final @RequestParam String products,
                                final @RequestParam(value = "client_id") String clientId,
                                final @RequestParam String destination) throws BusinessException {
        log.info("-SMAccessController.smEntry{} ");
        SMAccess sMAccess = getSMAccess(timestamp, country, channelId, token, products, clientId, destination);
        return new ModelAndView(REDIRECT + this.apiGatewayBusiness.getGlobalPath(sMAccess.getCountry()) + this.apiGatewayBusiness.getValOnlineBBVAToken(Optional.of(this.apiGatewayBusiness.getMerchantId()).get(), activeProfile, sMAccess));
    }*/

    /*
    @GetMapping(value = LOGIN+"test/")
    public ResponseEntity<SMAccess> smEntryTest( @Valid final @RequestParam String workflow,
                                                final @RequestParam String timestamp,
                                                final @RequestParam OperatingCountry country,
                                                final @RequestParam(value = "channel_id") Integer channelId,
                                                final @RequestParam String token,
                                                final @RequestParam String products,
                                                final @RequestParam(value = "client_id") String clientId,
                                                final @RequestParam String destination) throws BusinessException {
        log.info("-SMAccessController.smEntry{} " + workflow);
        //log.info("-SMAccessController.sMAccess{} " + sMAccess);
        System.out.println("===========WEve GOT de $$$$$=========");
        System.out.println("====workflow: "+workflow);
        System.out.println("====timestamp: "+timestamp);
        System.out.println("====country: "+country);
        System.out.println("====channelId: "+channelId);
        if(StringUtils.isNotBlank(timestamp) && country!= null && channelId != null &&  StringUtils.isNotBlank(token)
         && StringUtils.isNotBlank(products)  && StringUtils.isNotBlank(clientId) && StringUtils.isNotBlank(destination)){
            SMAccess sMAccess = getSMAccess(timestamp, country, channelId, token, products, clientId, destination);
            return ResponseEntity.ok().body(sMAccess);
        }
        return  ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(getSMAccess(timestamp, country, channelId, token, products, clientId, destination));
        //return  ResponseEntity.status(HttpStatus.OK).body("Ok");
    }
     */
}
