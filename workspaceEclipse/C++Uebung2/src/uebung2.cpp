#include <iostream>
#include <string>

// encrypt
std::string encrypt(const std::string& input, int offset) {
    std::string encrypted_string_;
    auto size_ = input.size();
    // iterate over input
    for(int i = 0; i < size_; i++) { //****FOR EACH BESSER****
        // temporary variable to handle each char
        char tmp = input[i];
        // test for positive offset
        if(offset >= 0) {
            for(int j = 0; j < offset; j++) { //****MODULO BESSER****
                // test whether char is in value range and let it unchanged
                // if it is not in range (e.g. /{1 !, not in value range)
                if(tmp <= 64 || (tmp >= 91 && tmp <= 96) || tmp >= 123) {
                   break;
                // go to start of Circuit if value range is left
                } else if(tmp + 1 == '[') {
                    tmp = 64;
                } else if(tmp + 1 == 123) {
                   tmp = 96;
                }
                // go a sign further
                tmp += 1;
            }
        } else { //negative offset
            for(int j = 0; j > offset; j--) {
                if(tmp <= 64 || (tmp >= 91 && tmp <= 96) || tmp >= 123) {
                    break;
                } else if(tmp - 1 == 64) {
                    tmp = 91;
                } else if(tmp - 1 == 96) {
                    tmp = 123;
                }
                tmp -= 1;
            }
        }
        encrypted_string_ += tmp;
    }
    return encrypted_string_;
}
// decrypt
// only content of if-block and else-block in second if statement
// in encrypt method swapped
//****EINFACH NUR ENCRYPT MIT NEGATIVEN OFFSET****
std::string decrypt(const std::string& input, int offset) {
    std::string decrypted_string_;
    auto size_ = input.size();
    for(int i = 0; i < size_; i++) {
        char tmp = static_cast<char>(input[i]);
        if(offset >= 0) {
            for(int j = 0; j < offset; j++) {
                if(tmp <= 64 || (tmp >= 91 && tmp <= 96) || tmp >= 123) {
                   break;
                } else if(tmp - 1 == 64) {
                    tmp = 91;
                } else if(tmp - 1 == 96) {
                    tmp = 123;
                }
                tmp -= 1;
            }
        } else { //negative offset
            for(int j = 0; j > offset; j--) {
                if(tmp <= 64 || (tmp >= 91 && tmp <= 96) || tmp >= 123) {
                    break;
                } else if(tmp + 1 == 91) {
                    tmp = 64;
                } else if(tmp + 1 == 123) {
                   tmp = 96;
                }
                tmp += 1;
            }
        }
        tmp = static_cast<char>(tmp);
        decrypted_string_ += tmp;
    }
    return decrypted_string_;
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
