#include <iostream>
#include <string>

// encrypt
std::string encrypt(const std::string& input, int offset) {
    std::string encrypted_string_;
    for(char ch : input) {
    	if(isalpha(ch)){	//is character a letter?
    		char base = islower(ch) ? 'a' : 'A';
    		if(offset < 0)	//converting negative offset to positive, because
    						//modulo with negative numbers is not treated like
    						//expected in C++
    			offset = ((offset * -1) - 26) % 26 * -1;
    		encrypted_string_ += (ch - base + offset) % 26 + base;
    	} else {	//simply add the characters which are no letter
    		encrypted_string_ += ch;
    	}
    }
    return encrypted_string_;
}
// decrypt
std::string decrypt(const std::string& input, int offset) {
    return encrypt(input, offset * (-1));
}

void test(const std::string& message,
          const std::string& actual,
          const std::string& expected) {
        std::cout << message;

if(actual==expected) {
    std::cout << "Korrekt: " << actual << "\n";
} else {
    std::cout << "Falsch!\n"
              << " Erwartet : " << expected << "\n"
              << " Wert : " << actual << "\n";
    }
}

int main() {
    const auto input = std::string("Test Nachricht 1");

    const auto result_4 = encrypt(input, 4);
    test("encrypt(..., 4) : ", result_4, "Xiwx Reglvmglx 1");
    test("decrypt(..., 4) : ", decrypt(result_4, 4), input);

    const auto result_42 = encrypt(input,-42);
    test("encrypt(...,-42): ", result_42, "Docd Xkmrbsmrd 1");
    test("decrypt(...,-42): ", decrypt(result_42,-42), input);
}
