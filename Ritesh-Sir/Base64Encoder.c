#include <stdio.h>
#include <string.h>

char base64_table[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

void base64_encode(const unsigned char *input, int len, char *output) {
    int i, j;
    for (i = 0, j = 0; i < len;) {
        unsigned int octet_1 = i < len ? input[i++] : 0;
        unsigned int octet_2 = i < len ? input[i++] : 0;
        unsigned int octet_3 = i < len ? input[i++] : 0;

        unsigned int triple = (octet_1 << 16) + (octet_2 << 8) + octet_3;

        output[j++] = base64_table[(triple >> 18) & 0x3F];
        output[j++] = base64_table[(triple >> 12) & 0x3F];
        output[j++] = (i > len + 1) ? '=' : base64_table[(triple >> 6) & 0x3F];
        output[j++] = (i > len)     ? '=' : base64_table[triple & 0x3F];
    }
    output[j] = '\0';
}

int main() {
    char text[] = "Man";
    char encoded[50];
    
    base64_encode((unsigned char *)text, strlen(text), encoded);
    printf("%s\n",text);
    printf("%s\n",encoded);
    return 0;
}

