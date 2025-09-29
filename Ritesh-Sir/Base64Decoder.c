#include <stdio.h>
#include <string.h>

int base64_reverse_table[256];

char base64_table[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

void reverseTable() {
    for (int i = 0; i < 256; i++) {
        base64_reverse_table[i] = -1;
    }
    for (int i = 0; i < 64; i++) {
        base64_reverse_table[(unsigned char)base64_table[i]] = i;
    }
    base64_reverse_table['='] = 0;
}

int base64_decode(const char *input, unsigned char *output) {
    int len = strlen(input);
    int i, j;
    int out_len = 0;

    for (i = 0, j = 0; i < len;) {
        unsigned int first_a = input[i] == '=' ? 0 & i++ : base64_reverse_table[(unsigned char)input[i++]];
        unsigned int second_b = input[i] == '=' ? 0 & i++ : base64_reverse_table[(unsigned char)input[i++]];
        unsigned int third_c = input[i] == '=' ? 0 & i++ : base64_reverse_table[(unsigned char)input[i++]];
        unsigned int fourth_d = input[i] == '=' ? 0 & i++ : base64_reverse_table[(unsigned char)input[i++]];

        unsigned int triple = (first_a << 18) +
                              (second_b << 12) +
                              (third_c << 6) +
                              fourth_d;

        if (input[i - 2] != '=') output[out_len++] = (triple >> 16) & 0xFF;
        if (input[i - 1] != '=') output[out_len++] = (triple >> 8) & 0xFF;
        if (input[i]     != '=') output[out_len++] = triple & 0xFF;
    }

    return out_len;
}

int main() {
    reverseTable();

    char encoded[] = "TWFu";
    unsigned char decoded[50];

    int decoded_len = base64_decode(encoded, decoded);
    decoded[decoded_len] = '\0';
    printf("%s\n",encoded);
    printf("%s\n",decoded);

    return 0;
}

