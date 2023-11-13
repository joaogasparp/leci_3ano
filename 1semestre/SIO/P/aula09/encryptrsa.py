import sys
from cryptography.hazmat.primitives import serialization
from cryptography.hazmat.primitives.asymmetric import padding

if __name__ == "__main__":
    
    if(len(sys.argv) != 4):
        print("a_keygen pubkey_file privkey_file 2048") 
        
    with open(sys.argv[3], "rb") as key_file:
        private_key = serialization.load_pem_public_key(
            key_file.read(),
        )
        
        fsrc = open(sys.argv[1], "rb")
        with open(sys.argv[2], "wb") as fdst:
            fdst.write(b'')
            
        fdst = open(sys.argv[2], "ab")
        while True:
            text = fdst.read(117)
            if not text:
                break
            ciphertext = private_key.encrypt(
                text,
                padding.PKCS1v15(),
            )
            fdst.write(ciphertext)
        fsrc.close()
        fdst.close()
        
#-----------------------------------------------------------------------#
# COMANDO PARA EXECUTAR O PROGRAMA:                                     #
# python encryptrsa.py ./plaintext.txt ./plaintext_crypt.txt ./pub.pem  #
#-----------------------------------------------------------------------#
