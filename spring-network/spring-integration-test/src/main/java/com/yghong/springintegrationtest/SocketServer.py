import socket

HOST = '0.0.0.0'  # 호스트 이름
PORT = 12345        # 포트 번호

# IPv4 체계, TCP 타입 소켓 객체를 생성
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# 포트 번호 재사용
server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

# bind 함수 호출
server_socket.bind((HOST, PORT))

# server 설정
server_socket.listen()

print('server start')
print('IP: {}, Port: {}'.format(HOST, PORT))

# 클라이언트가 접속하면 accept 함수가 호출됩니다.
# 연결된 클라이언트 소켓 객체와 주소를 리턴합니다.
client_socket, addr = server_socket.accept()

# 클라이언트가 접속하면 상대방의 주소가 나옵니다.
print('Connected by', addr)

# 무한루프를 돌면서 클라이언트의 메시지를 받고
# 다시 클라이언트에게 그대로 보냅니다.
while True:
	data = client_socket.recv(1024)
	if not data:
		break

	client_socket.sendall(data)

# 소켓을 닫습니다.
client_socket.close()
server_socket.close()