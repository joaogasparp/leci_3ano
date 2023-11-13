import sys
from cryptography.hazmat.primitives import serialization
from cryptography.hazmat.primitives.asymmetric import rsa


def genkey(size):
    valid_sizes = [1024, 2048, 3072, 4096]
    if int(size) not in valid_sizes:
        print("Invalid key size")
        exit(1)
        
    private_key = rsa.generate_private_key(
        public_exponent=65537,
        key_size=int(size),
    )
    return private_key

if __name__ == "__main__":
    
    if (len(sys.argv) != 4):
        print("a_keygen pubkey_file privkey_file 2048") 
        
    privkey = genkey(sys.argv[3])
    pubkey = privkey.public_key()
    
    pem = privkey.private_bytes(
        encoding = serialization.Encoding.PEM,
        format = serialization.PrivateFormat.TraditionalOpenSSL,
        encryption_algorithm = serialization.NoEncryption(),
    )
    
    with open(sys.argv[2], 'wb') as fwr:
        fwr.write(pem)
        
    pem = pubkey.public_bytes(
        encoding = serialization.Encoding.PEM,
        format = serialization.PublicFormat.SubjectPublicKeyInfo,
    )
    
    with open(sys.argv[1], 'wb') as fwr:
        fwr.write(pem)
        
#-----------------------------------------------------------------------#
# COMANDO PARA EXECUTAR O PROGRAMA:                                     #
# time python keygen.py ./pub.txt ./priv.txt 4096                       #
#-----------------------------------------------------------------------#
