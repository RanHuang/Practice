## 执行playbook
`ansible-playbook web-notls.yml`
## 生成自签发证书 
`openssl req -x509 -nodes -days 3650 -newkey rsa:2048 -subj /CN=localhost -keyout files/nginx.key -out files/nginx.crt`

## 网页访问
1. 普通http请求  
  http://localhost:8080/   
2. TLS请求  
  https://localhost:8443/  
