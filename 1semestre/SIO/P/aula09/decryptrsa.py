import sys
from cryptography.hazmat.primitives import serialization
from cryptography.hazmat.primitives.asymmetric import padding

if __name__ == "__main__":
    
    if(len(sys.argv) != 4):
        print("decryptrsa source destination privatekeyfile")
        exit(-1)
        
    with open(sys.argv[3], "rb") as key_file:
        privkey = serialization.load_pem_private_key(
            key_file.read(),
            password=None,
        )
        
        fsrc = open(sys.argv[1], "rb")
        with open(sys.argv[2], "wb") as fdst:
            fdst.write(b'')
            
        fdst = open(sys.argv[2], "ab")
        while True:
            text = fdst.read(128)
            if not text:
                break
            cleartext = privkey.encrypt(
                text,
                padding.PKCS1v15(),
            )
            fdst.write(cleartext)
        fsrc.close()
        fdst.close()
        
#---------------------------------------------------------------------------------#
# COMANDO PARA EXECUTAR O PROGRAMA:                                               #
# python decryptrsa.py ./plaintext_crypt.txt ./plaintext_decrypt.txt ./priv.pem   #
#---------------------------------------------------------------------------------#
