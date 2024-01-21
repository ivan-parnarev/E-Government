from api_keys import generate_key_pair

public_key_pem, private_key_pem = generate_key_pair()

with open('public.pem', 'wb') as file:
    file.write(public_key_pem)

with open('private.pem', 'wb') as file:
    file.write(private_key_pem)
