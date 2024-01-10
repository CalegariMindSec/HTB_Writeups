import base64

enc_password = "0Nv32PTwgYjzg9/8j5TbmvPd3e7WhtWWyuPsyO76/Y+U193E"
key = b"armando"

def decrypt_password(encoded_password, decryption_key):
    decoded_bytes = base64.b64decode(encoded_password)
    key_length = len(decryption_key)
    decrypted_bytes = bytearray(decoded_bytes)

    for i in range(len(decoded_bytes)):
        decrypted_bytes[i] = (decoded_bytes[i] ^ decryption_key[i % key_length] ^ 0xDF)

    return decrypted_bytes.decode('latin-1')

# Chamada da função para obter e imprimir a senha descriptografada
senha_descriptografada = decrypt_password(enc_password, key)
print("Senha descriptografada:", senha_descriptografada)


